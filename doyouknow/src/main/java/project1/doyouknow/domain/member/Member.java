package project1.doyouknow.domain.member;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.domain.base.BaseEntity;
import project1.doyouknow.domain.comment.Comment;
import project1.doyouknow.domain.likes.PostLikes;
import project1.doyouknow.domain.post.saveForm.Post;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Membership membership;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<PostLikes> postLikes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    private int deletePostCount;
    private int postCount;

    public void initMember() {
        this.membership = Membership.BASIC;
    }
    //Post 생성
    public void postCreate()
    {
        this.postCount++;
        checkMembership();
    }
    //Post 삭제
    public void delete() {
        this.postCount--;
        this.deletePostCount++;
        checkMembership();
    }
    //Post 백업
    public void create()
    {   this.postCount++;
        this.deletePostCount--;
        checkMembership();
    }

    public void checkMembership() {
        if (this.postCount >= 3) {
            this.membership = Membership.VIP;
        } else {
            this.membership = Membership.BASIC;
        }
    }
}
