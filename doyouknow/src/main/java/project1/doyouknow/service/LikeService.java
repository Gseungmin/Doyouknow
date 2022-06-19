package project1.doyouknow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project1.doyouknow.domain.board.Board;
import project1.doyouknow.domain.board.SortedBoardForm;
import project1.doyouknow.domain.image.FileStore;
import project1.doyouknow.domain.image.Image;
import project1.doyouknow.domain.likes.PostLikes;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.domain.post.editForm.PersonEditForm;
import project1.doyouknow.domain.post.editForm.PlaceEditForm;
import project1.doyouknow.domain.post.editForm.VideoEditForm;
import project1.doyouknow.domain.post.saveForm.Person;
import project1.doyouknow.domain.post.saveForm.Place;
import project1.doyouknow.domain.post.saveForm.Post;
import project1.doyouknow.domain.post.saveForm.Video;
import project1.doyouknow.repository.LikeRepository;
import project1.doyouknow.repository.MemberRepository;
import project1.doyouknow.repository.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {


    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /**Like Post**/
    public void clickLikes(String loginId, Long postId) {
        Post post = postRepository.findById(postId);
        Optional<Member> OpMember = memberRepository.findByLoginId(loginId);
        Member member = OpMember.get();

        Optional<PostLikes> OpLikes = member.getPostLikes().stream().filter(postLikes -> postLikes.getPost().getId().equals(postId)).findFirst();
        //멤버가 해당 게시물에 좋아요나 싫어요를 눌렀다면
        if (OpLikes.isPresent()) {
            PostLikes postLikes = OpLikes.get();
            if (postLikes.getLikesOrHates() == 2) { //싫어요를 눌렀다면
                postLikes.setLikesOrHates(1);
                post.decreaseHate();
                post.increaseLike();
            } else if (postLikes.getLikesOrHates() == 1) { //좋아요를 눌렀다면
                postLikes.setLikesOrHates(0);
                post.decreaseLike();
            } else if (postLikes.getLikesOrHates() == 0) { //아무것도 누르지 않은 상태라면
                postLikes.setLikesOrHates(1);
                post.increaseLike();
            }
        } else if (OpLikes.isEmpty()){ //멤버가 해당 게시물에 좋아요나 싫어요를 누르지 않았다면
            PostLikes postLikes = new PostLikes();
            postLikes.setLikesOrHates(1);
            post.increaseLike();
            likeRepository.save(postLikes);
            /**postlikes와 post 양방향 연관관계, postlikes member 양방향 연관관계
            postLikes.setPost(post);
            post.getPostLikes().add(postLikes);
            postLikes.setMember(member);
            member.getPostLikes().add(postLikes); **/
            postLikes.addMember(member);
            postLikes.addPost(post);
        }
    }

    /**Hate Post**/
    public void clickHates(String loginId, Long postId) {
        Post post = postRepository.findById(postId);
        Optional<Member> OpMember = memberRepository.findByLoginId(loginId);
        Member member = OpMember.get();

        Optional<PostLikes> OpLikes = member.getPostLikes().stream().filter(postLikes -> postLikes.getPost().getId().equals(postId)).findFirst();
        //멤버가 해당 게시물에 좋아요나 싫어요를 눌렀다면
        if (OpLikes.isPresent()) {
            PostLikes postLikes = OpLikes.get();
            if (postLikes.getLikesOrHates() == 2) { //싫어요를 눌렀다면
                postLikes.setLikesOrHates(0);
                post.decreaseHate();
            } else if (postLikes.getLikesOrHates() == 1) { //좋아요를 눌렀다면
                postLikes.setLikesOrHates(2);
                post.decreaseLike();
                post.increaseHate();
            } else if (postLikes.getLikesOrHates() == 0) { //아무것도 누르지 않은 상태라면
                postLikes.setLikesOrHates(2);
                post.increaseHate();
            }
        } else if (OpLikes.isEmpty()){ //멤버가 해당 게시물에 좋아요나 싫어요를 누르지 않았다면
            PostLikes postLikes = new PostLikes();
            postLikes.setLikesOrHates(2);
            post.increaseHate();
            likeRepository.save(postLikes);
            /**postlikes와 post 양방향 연관관계, postlikes member 양방향 연관관계
            postLikes.setPost(post);
            post.getPostLikes().add(postLikes);
            postLikes.setMember(member);
            member.getPostLikes().add(postLikes);**/
            postLikes.addMember(member);
            postLikes.addPost(post);
        }
    }
}
