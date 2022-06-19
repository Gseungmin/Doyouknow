package project1.doyouknow.api.dto.errorDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ErrorInfo {

    private String code;
    private String message;
}
