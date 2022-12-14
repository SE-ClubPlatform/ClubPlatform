package SW_Engineering.Group3.domain.comment;

import SW_Engineering.Group3.domain.Board.Anonymous;
import SW_Engineering.Group3.domain.Board.Community;
import SW_Engineering.Group3.domain.auth.Member;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Getter
@RequiredArgsConstructor
public class CommunityComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "community_post_id")
    private Community posts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member; // 작성자

    @Builder
    public CommunityComment(Long id, String content, Community posts, Member member) {
        this.id = id;
        this.content = content;
        this.posts = posts;
        this.member = member;
    }
}
