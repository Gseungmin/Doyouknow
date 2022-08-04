package project1.doyouknow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project1.doyouknow.domain.comment.Comment;

@Repository
public interface CommentSpringJpaRepository extends JpaRepository<Comment, Long> {
}
