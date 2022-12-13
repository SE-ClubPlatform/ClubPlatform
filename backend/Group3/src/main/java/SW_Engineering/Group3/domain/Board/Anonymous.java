package SW_Engineering.Group3.domain.Board;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.comment.AnonymousComment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Anonymous extends Board {
    private Boolean isAnonymous;
    @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    @JsonIgnoreProperties({"posts"})
    private List<AnonymousComment> comments;

    @Builder
    public Anonymous(Long boardID, String title, String content, Member author, LocalDateTime createDate, LocalDateTime createTime, int commentCount, Boolean isAnonymous, List<AnonymousComment> comments) {
        super(boardID, title, content, author, createDate, createTime, commentCount);
        this.isAnonymous = isAnonymous;
        this.comments = comments;
    }
}
