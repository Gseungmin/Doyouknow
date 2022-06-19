package project1.doyouknow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project1.doyouknow.domain.comment.Comment;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.domain.post.saveForm.Post;
import project1.doyouknow.repository.CommentRepository;
import project1.doyouknow.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    public void makeComment(Comment comment, Post post, Member member) {
        /**comment와 post, comment와 member의 양방향 연관관계
        //post comment connect
        post.getComments().add(comment);
        comment.setPost(post);
        //member comment connect
        comment.setMember(member);**/
        comment.addPost(post);
        comment.addMember(member);
        commentRepository.save(comment);
    }

}
