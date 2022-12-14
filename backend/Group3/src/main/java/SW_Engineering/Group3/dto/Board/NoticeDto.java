package SW_Engineering.Group3.dto.Board;

import SW_Engineering.Group3.domain.Board.Notice;
import SW_Engineering.Group3.domain.auth.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeDto {

    private Long noticeId;
    private String title;
    private String author;
    private String content;
    private Boolean isFinish;
    private String createTime;

    @Builder
    public NoticeDto(Long noticeId, String title, String author, String content,
                     Boolean isFinish, String createTime) {
        this.noticeId = noticeId;
        this.title = title;
        this.author = author;
        this.content = content;
        this.isFinish = isFinish;
        this.createTime = createTime;
    }

    public Notice toNotice(Member author) {
        return Notice.builder()
                .author(author)
                .title(title)
                .content(content)
                .isFinish(isFinish)
                .build();
    }
}
