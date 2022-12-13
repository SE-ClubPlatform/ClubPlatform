package SW_Engineering.Group3.dto.Board;

import SW_Engineering.Group3.domain.Board.Category;
import SW_Engineering.Group3.domain.Board.Community;
import SW_Engineering.Group3.domain.auth.Member;

import java.time.LocalDateTime;

public class CommunityDto {
    private String title;
    private String content;
    private Member author;
    private LocalDateTime createDate;
    private LocalDateTime createTime;
    private int commentCount;
    private Category category;

    public CommunityDto(String title, String content, Member author, LocalDateTime createDate, LocalDateTime createTime, int commentCount, Category category) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createDate = createDate;
        this.createTime = createTime;
        this.commentCount = commentCount;
        this.category = category;
    }

    public Community toCommunity() {
        return Community.builder()
                .title(title)
                .content(content)
                .author(author)
                .createDate(createDate)
                .createTime(createTime)
                .commentCount(commentCount)
                .category(category)
                .build();
    }
}
