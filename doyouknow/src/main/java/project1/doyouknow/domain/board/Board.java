package project1.doyouknow.domain.board;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.domain.base.BaseEntity;
import project1.doyouknow.domain.post.saveForm.Post;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Board extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long id;

    private String type;

    private String message;

    private int boardNum;

    private int postCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<Post> posts = new ArrayList<>();

    //post 생성
    public void create() {
        this.postCount++;
    }

    //post 삭제
    public void delete() {
        this.postCount--;
    }
}
