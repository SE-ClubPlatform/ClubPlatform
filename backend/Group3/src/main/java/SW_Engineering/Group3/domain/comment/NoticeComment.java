package SW_Engineering.Group3.domain.comment;

import SW_Engineering.Group3.domain.Board.Notice;
import SW_Engineering.Group3.domain.auth.Member;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Getter
@RequiredArgsConstructor
public class NoticeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "notice_post_id")
    private Notice posts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member; // 작성자

    public NoticeComment(Long id, String content, Notice posts, Member member) {
        this.id = id;
        this.content = content;
        this.posts = posts;
        this.member = member;
    }
}
