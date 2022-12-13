package SW_Engineering.Group3.dto.Comment;

import SW_Engineering.Group3.domain.comment.CommunityComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityCommentDto {
    private Long id;
    private String content;
    private String writer;

    public static CommunityCommentDto toCommunity(CommunityComment CommunityComment) {
        return new CommunityCommentDto(
                CommunityComment.getId(),
                CommunityComment.getContent(),
                CommunityComment.getMember().getUserName()
        );
    }
}
