package SW_Engineering.Group3.dto.Board;

import SW_Engineering.Group3.domain.Board.Anonymous;
import SW_Engineering.Group3.domain.auth.Member;

import java.time.LocalDateTime;

public class AnonymousDto {
    private String title;
    private String content;
    private Member author;
    private LocalDateTime createDate;
    private LocalDateTime createTime;
    private int commentCount;
    private Boolean isAnonymous;

    public AnonymousDto(String title, String content, Member author, LocalDateTime createDate, LocalDateTime createTime, int commentCount, Boolean isAnonymous) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createDate = createDate;
        this.createTime = createTime;
        this.commentCount = commentCount;
        this.isAnonymous = isAnonymous;
    }

    public Anonymous toAnonymous() {
        return Anonymous.builder()
                .title(title)
                .content(content)
                .author(author)
                .createDate(createDate)
                .createTime(createTime)
                .commentCount(commentCount)
                .isAnonymous(isAnonymous)
                .build();
    }
}
