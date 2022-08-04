package project1.doyouknow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project1.doyouknow.domain.likes.PostLikes;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.domain.post.saveForm.Post;
import project1.doyouknow.repository.*;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {


    private final LikeSpringJpaRepository likeRepository;
    private final PostSpringJpaRepository postRepository;
    private final MemberSpringJpaRepository memberRepository;

    /**Like Post**/
    public void clickLikes(String loginId, Long postId) {
        Optional<Post> OpPost = postRepository.findById(postId);
        Optional<Member> OpMember = memberRepository.findByLoginId(loginId);
        Post post = OpPost.get();
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
        Optional<Post> OpPost = postRepository.findById(postId);
        Optional<Member> OpMember = memberRepository.findByLoginId(loginId);
        Post post = OpPost.get();
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
