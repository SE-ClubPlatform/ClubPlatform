package SW_Engineering.Group3.dto.Comment;

import SW_Engineering.Group3.domain.Board.Anonymous;
import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.comment.AnonymousComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnonymousCommentDto {
    private Long id;
    private String content;
    private String writer;

    public static AnonymousCommentDto toAnonymous(AnonymousComment anonymousComment) {
        return new AnonymousCommentDto(
                anonymousComment.getId(),
                anonymousComment.getContent(),
                anonymousComment.getMember().getUserName()
        );
    }
}
