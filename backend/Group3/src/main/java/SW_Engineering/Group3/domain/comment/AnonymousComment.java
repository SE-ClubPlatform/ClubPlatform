package SW_Engineering.Group3.domain.comment;

import SW_Engineering.Group3.domain.Board.Anonymous;
import SW_Engineering.Group3.domain.auth.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Data
@Entity
@Getter
@RequiredArgsConstructor
public class AnonymousComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "anonymous_post_id")
    private Anonymous posts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member; // 작성자

    @Builder
    public AnonymousComment(Long id, String content, Anonymous posts, Member member) {
        this.id = id;
        this.content = content;
        this.posts = posts;
        this.member = member;
    }
}
