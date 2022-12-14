package SW_Engineering.Group3.domain.Board;

import SW_Engineering.Group3.domain.auth.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community extends Board {
    private Category category;

    @Builder
    public Community(String author, Long boardID, String title, String content, Category category) {
        super(author, boardID, title, content);
        this.category = category;
    }
}
