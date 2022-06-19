package project1.doyouknow.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project1.doyouknow.domain.board.Board;
import project1.doyouknow.domain.post.saveForm.Post;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }

    public Post findById(Long id) {
        return em.find(Post.class, id);
    }

    public List<Board> findAllBoard() {
        return em.createQuery("select b from Board b", Board.class).getResultList();
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class).getResultList();
    }

}
