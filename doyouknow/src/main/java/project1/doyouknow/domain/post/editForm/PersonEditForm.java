package project1.doyouknow.domain.post.editForm;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.domain.image.Image;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class PersonEditForm {
    private String name;
    private String content;
    private String birth;
    private Image image;
}
