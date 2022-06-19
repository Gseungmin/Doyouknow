package project1.doyouknow.domain.post.saveForm;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter @Setter
@DiscriminatorValue("PLACE")
public class Place extends Post {
    @NotEmpty
    private String address;
}
