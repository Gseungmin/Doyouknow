package project1.doyouknow.api.dto.boardDto;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.api.dto.postDto.PostContentDto;
import project1.doyouknow.domain.board.Board;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class BoardPostDto {

    private String name;
    private List<PostContentDto> postContentDtos;

    public BoardPostDto(Board board) {
        this.name = board.getType();
        this.postContentDtos = board.getPosts().stream().map(post -> new PostContentDto(post)).collect(Collectors.toList());
    }
}
