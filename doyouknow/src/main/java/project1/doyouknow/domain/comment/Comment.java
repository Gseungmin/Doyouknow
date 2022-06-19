package project1.doyouknow.domain.comment;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.domain.base.BaseEntity;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.domain.post.saveForm.Post;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter @Setter
public class Comment extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;

    @NotEmpty
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void addMember(Member member) {
        this.member = member;
        member.getComments().add(this);
    }

    public void addPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }
}
