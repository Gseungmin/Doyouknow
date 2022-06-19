package project1.doyouknow.domain.post.saveForm;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.domain.board.Board;
import project1.doyouknow.domain.comment.Comment;
import project1.doyouknow.domain.image.Image;
import project1.doyouknow.domain.likes.PostLikes;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.domain.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@DiscriminatorColumn(name = "POST")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Post extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "POST_ID")
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String content;
    private int likes;
    private int hates;
    private int view;
    private Boolean delete;

    //파일 업로드를 위한 이름 및 경로
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "IMAGE_ID")
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<PostLikes> postLikes = new ArrayList<>();

    //post member 양방향 연관관계
    public void addMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }

    //post board 양방향 연관관계
    public void addBoard(Board board) {
        this.board = board;
        board.getPosts().add(this);
    }

    //post image 양방향 연관관계
    public void addImage(Image image) {
        this.image = image;
        image.setPost(this);
    }

     public void increaseLike() {
     this.likes++;}

     public void increaseHate() {
     this.hates++;}

    public void decreaseLike() {
        this.likes--;}

    public void decreaseHate() {
        this.hates--;}

}
