package project1.doyouknow.api.dto.postDto;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.domain.post.saveForm.Post;

@Getter @Setter
public class PostContentDto {

    private String name;
    private String content;

    public PostContentDto(Post post) {
        this.name = post.getName();
        this.content = post.getContent();
    }
}
