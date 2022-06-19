package project1.doyouknow.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project1.doyouknow.domain.board.Board;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.domain.member.Membership;
import project1.doyouknow.domain.post.saveForm.Post;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QueryRepository {

    private final EntityManager em;

    public List<Member> findMemberAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findMemberVipAll() {
        return em.createQuery("select m from Member m where m.membership = :memberType" , Member.class)
                .setParameter("memberType", Membership.VIP).getResultList();
    }

    public List<Post> findPostAll() {
        return em.createQuery("select p from Post p" +
                " join fetch p.image i" +
                " join fetch p.member m" +
                " join fetch p.board b").getResultList();
    }

    public List<Post> findPostLikesMoreThan(int count) {
        return em.createQuery("select p from Post p" +
                " join fetch p.image i" +
                " join fetch p.member m" +
                " join fetch p.board b" +
                "  where p.likes >= :count").setParameter("count", count).getResultList();
    }

    public List<Board> findBoardAll() {
        return em.createQuery("select b from Board b").getResultList();
    }




}
