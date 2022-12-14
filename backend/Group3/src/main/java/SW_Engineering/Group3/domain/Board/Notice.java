package SW_Engineering.Group3.domain.Board;

import SW_Engineering.Group3.domain.auth.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends Board {
    private Boolean isFinish;

    @Builder
    public Notice(String author, Long boardID, String title, String content, Boolean isFinish) {
        super(author, boardID, title, content);
        this.isFinish = isFinish;
    }
}
