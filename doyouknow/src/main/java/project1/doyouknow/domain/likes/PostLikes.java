package project1.doyouknow.domain.likes;

import lombok.Getter;
import lombok.Setter;
import project1.doyouknow.domain.base.BaseEntity;
import project1.doyouknow.domain.member.Member;
import project1.doyouknow.domain.post.saveForm.Post;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PostLikes extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "POST_LIKES_ID")
    private Long id;

    private int likesOrHates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void addPost(Post post) {
        this.post = post;
        post.getPostLikes().add(this);
    }

    public void addMember(Member member) {
        this.member = member;
        member.getPostLikes().add(this);
    }
}
