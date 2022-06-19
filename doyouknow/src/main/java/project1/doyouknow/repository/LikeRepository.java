package project1.doyouknow.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project1.doyouknow.domain.comment.Comment;
import project1.doyouknow.domain.likes.PostLikes;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

    private final EntityManager em;

    public void save(PostLikes postLikes) {
        em.persist(postLikes);
    }

    public PostLikes findById(Long id) {
        return em.find(PostLikes.class, id);
    }

    public List<PostLikes> findAll() {
        return em.createQuery("select pl from PostLikes pl", PostLikes.class).getResultList();
    }

}
