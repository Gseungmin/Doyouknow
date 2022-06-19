package project1.doyouknow.api.dto.memberDto;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.api.dto.commentDto.CommentContentDto;
import project1.doyouknow.api.dto.postDto.PostContentDto;
import project1.doyouknow.domain.member.Member;

import java.util.List;
import java.util.stream.Collectors;


@Getter @Setter
public class MemberPostCommentDto {

    private Long id;
    private String name;
    private List<PostContentDto> postContentDtos;
    private List<CommentContentDto> commentContentDtos;

    public MemberPostCommentDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.postContentDtos = member.getPosts().stream().map(post -> new PostContentDto(post)).collect(Collectors.toList());
        this.commentContentDtos = member.getComments().stream().map(comment -> new CommentContentDto(comment)).collect(Collectors.toList());
    }
}
