package SW_Engineering.Group3.dto.Board;

import SW_Engineering.Group3.domain.Board.Notice;
import SW_Engineering.Group3.domain.auth.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeDto {
    private String title;
    private String content;
    private Member author;
    private LocalDateTime createDate;
    private LocalDateTime createTime;
    private int commentCount;
    private Boolean isFinish;

    @Builder
    public NoticeDto(String title, String content, Member author, LocalDateTime createDate, LocalDateTime createTime, int commentCount, Boolean isFinish) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createDate = createDate;
        this.createTime = createTime;
        this.commentCount = commentCount;
        this.isFinish = isFinish;
    }

    public Notice toNotice() {
        return Notice.builder()
                .title(title)
                .content(content)
                .author(author)
                .createDate(createDate)
                .createTime(createTime)
                .commentCount(commentCount)
                .isFinish(isFinish)
                .build();
    }
}
