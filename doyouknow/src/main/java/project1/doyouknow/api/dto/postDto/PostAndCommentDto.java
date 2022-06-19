package project1.doyouknow.api.dto.postDto;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.api.dto.commentDto.CommentContentDto;
import project1.doyouknow.domain.post.saveForm.Post;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class PostAndCommentDto {

    private Long id;
    private String name;
    private String content;
    private List<CommentContentDto> commentDtos;

    public PostAndCommentDto(Post post) {
        this.id = post.getId();
        this.name = post.getName();
        this.content = post.getContent();
        this.commentDtos = post.getComments().stream().map(comment -> new CommentContentDto(comment)).collect(Collectors.toList());
    }
}
