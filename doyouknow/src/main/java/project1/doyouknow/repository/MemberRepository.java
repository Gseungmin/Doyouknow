package project1.doyouknow.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project1.doyouknow.domain.member.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream().filter(member -> member.getLoginId().equals(loginId)).findFirst();
    }

    public Optional<Member> findByNickname(String nickname) {
        return findAll().stream().filter(member -> member.getNickname().equals(nickname)).findFirst();
    }
}
