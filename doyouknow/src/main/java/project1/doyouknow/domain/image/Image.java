package project1.doyouknow.domain.image;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.domain.base.BaseEntity;
import project1.doyouknow.domain.post.saveForm.Post;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Image extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "IMAGE_ID")
    private Long id;
    private String uploadFileName;
    private String storeFileName;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "image")
    private Post post;
}
