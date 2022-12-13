package SW_Engineering.Group3.domain.Board;

import SW_Engineering.Group3.domain.auth.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community extends Board {
    private Category category;

    @Builder
    public Community(Long boardID, String title, String content, Member author, LocalDateTime createDate, LocalDateTime createTime, int commentCount, Category category) {
        super(boardID, title, content, author, createDate, createTime, commentCount);
        this.category = category;
    }
}
