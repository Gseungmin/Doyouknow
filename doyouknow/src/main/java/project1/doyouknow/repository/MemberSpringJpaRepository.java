package project1.doyouknow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project1.doyouknow.domain.member.Member;

import java.util.Optional;

@Repository
public interface MemberSpringJpaRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);
    Optional<Member> findByNickname(String nickname);
}
