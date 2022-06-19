package project1.doyouknow.domain.post.editForm;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.domain.image.Image;

@Getter @Setter
public class VideoEditForm {
    private String name;
    private String content;
    private String director;
    private Image image;
}
