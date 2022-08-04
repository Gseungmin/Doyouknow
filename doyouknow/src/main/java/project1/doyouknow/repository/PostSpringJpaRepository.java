package project1.doyouknow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project1.doyouknow.domain.post.saveForm.Post;

@Repository
public interface PostSpringJpaRepository extends JpaRepository<Post, Long> {
}
