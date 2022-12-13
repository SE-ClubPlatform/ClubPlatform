package SW_Engineering.Group3.dto.Comment;

import SW_Engineering.Group3.domain.comment.NoticeComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeCommentDto {
    private Long id;
    private String content;
    private String writer;

    public static NoticeCommentDto toNotice(NoticeComment noticeComment) {
        return new NoticeCommentDto(
                noticeComment.getId(),
                noticeComment.getContent(),
                noticeComment.getMember().getUserName()
        );
    }
}
