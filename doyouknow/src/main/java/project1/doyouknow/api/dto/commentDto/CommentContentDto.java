package project1.doyouknow.api.dto.commentDto;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.domain.comment.Comment;

@Getter @Setter
public class CommentContentDto {

    private Long id;
    private String content;

    public CommentContentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
