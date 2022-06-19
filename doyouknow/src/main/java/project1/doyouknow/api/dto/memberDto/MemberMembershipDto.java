package project1.doyouknow.api.dto.memberDto;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.domain.member.Membership;

@Getter @Setter
public class MemberMembershipDto {

    private Long id;
    private Membership membership;

    public MemberMembershipDto(Member member) {
        this.id = member.getId();
        this.membership = member.getMembership();
    }
}
