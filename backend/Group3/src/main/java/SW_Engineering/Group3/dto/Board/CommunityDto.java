package SW_Engineering.Group3.dto.Board;

import SW_Engineering.Group3.domain.Board.Category;
import SW_Engineering.Group3.domain.Board.Community;
import SW_Engineering.Group3.domain.auth.Member;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityDto {

    private Long communityId;
    private String title;
    private String author;
    private String content;
    private Category category;
    private String createTime;

    public CommunityDto(Long communityId, String title, String author, String content,
                        Category category, String createTime) {
        this.communityId = communityId;
        this.title = title;
        this.author = author;
        this.content = content;
        this.category = category;
        this.createTime = createTime;
    }

    public Community toCommunity() {
        return Community.builder()
                .author(author)
                .title(title)
                .content(content)
                .category(category)
                .build();
    }
}
