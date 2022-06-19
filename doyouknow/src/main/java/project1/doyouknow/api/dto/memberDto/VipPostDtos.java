package project1.doyouknow.api.dto.memberDto;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.api.dto.postDto.PostContentDto;
import project1.doyouknow.domain.member.Member;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class VipPostDtos {

    private String name;
    private List<PostContentDto> postContentDtos;

    public VipPostDtos(Member member) {
        this.name = member.getName();
        this.postContentDtos = member.getPosts().stream().map(post -> new PostContentDto(post)).collect(Collectors.toList());
    }
}
