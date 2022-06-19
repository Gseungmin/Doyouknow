package project1.doyouknow.domain.board;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.domain.post.saveForm.Post;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class SortedBoardForm {

    private Long boardId;
    private String type;
    private int boardNum;
    private int postCount;
    private String message;
    private List<Post> posts;

    public SortedBoardForm(Board board) {
        this.boardId = board.getId();
        this.type = board.getType();
        this.boardNum = board.getBoardNum();
        this.postCount = board.getPostCount();
        this.message = board.getMessage();
        this.posts = board.getPosts().stream().filter(post -> post.getDelete().equals(false)).collect(Collectors.toList());
    }
}
