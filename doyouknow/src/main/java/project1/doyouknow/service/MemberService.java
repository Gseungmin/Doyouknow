package project1.doyouknow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.repository.MemberSpringJpaRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberSpringJpaRepository memberRepository;

    public Optional<Member> login(String loginId, String password) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (findMember.isPresent()) {
            if (findMember.get().getPassword().equals(password)) {
                return findMember;
            }
        } return Optional.empty();
    }

    public Optional<Member> findAnyByLoginID(String loginId) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        return findMember;
    }

    public Optional<Member> findAnyByNickname(String nickname) {
        Optional<Member> findMember = memberRepository.findByNickname(nickname);
        return findMember;
    }

    public Optional<Member> findById(Long id){
        return memberRepository.findById(id);
    }

    public void join(Member member) {
        member.initMember();
        memberRepository.save(member);
    }
}
