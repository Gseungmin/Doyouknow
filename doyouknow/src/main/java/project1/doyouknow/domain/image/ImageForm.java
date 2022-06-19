package project1.doyouknow.domain.image;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class ImageForm {

    private MultipartFile imageFile;
}