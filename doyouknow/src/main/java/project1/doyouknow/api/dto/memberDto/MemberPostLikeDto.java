package project1.doyouknow.api.dto.memberDto;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.api.dto.postDto.LikePostDto;
import project1.doyouknow.domain.likes.PostLikes;
import project1.doyouknow.domain.member.Member;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class MemberPostLikeDto {

    private Long id;
    private String name;
    private List<LikePostDto> likePostDtos;

    public MemberPostLikeDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        List<PostLikes> postLikes = member.getPostLikes().stream().filter(postLike -> postLike.getLikesOrHates() == 1)
                .collect(Collectors.toList());
        this.likePostDtos = postLikes.stream().map(postLikesFiltered -> new LikePostDto(postLikesFiltered.getPost())).collect(Collectors.toList());
    }
}
