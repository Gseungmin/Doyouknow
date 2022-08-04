package project1.doyouknow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project1.doyouknow.domain.likes.PostLikes;

@Repository
public interface LikeSpringJpaRepository extends JpaRepository<PostLikes, Long> {
}
