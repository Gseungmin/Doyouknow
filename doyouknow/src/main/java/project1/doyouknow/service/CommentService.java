package project1.doyouknow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project1.doyouknow.domain.comment.Comment;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.domain.post.saveForm.Post;
import project1.doyouknow.repository.CommentSpringJpaRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentSpringJpaRepository commentRepository;

    public void makeComment(Comment comment, Post post, Member member) {
        comment.addPost(post);
        comment.addMember(member);
        commentRepository.save(comment);
    }

}
