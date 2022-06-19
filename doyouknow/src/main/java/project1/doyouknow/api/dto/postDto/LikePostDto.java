package project1.doyouknow.api.dto.postDto;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.domain.post.saveForm.Post;

@Getter @Setter
public class LikePostDto {

    private String name;
    private String content;

    public LikePostDto(Post post) {
        this.name = post.getName();
        this.content = post.getContent();
    }
}
