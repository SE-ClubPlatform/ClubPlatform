package SW_Engineering.Group3.dto.workflow;

import SW_Engineering.Group3.domain.workflow.VoteContent;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VoteContentDto {

    private Long voteContentId;
    private String content;
    private int count;

    public VoteContentDto(Long voteContentId, String content, int count) {
        this.voteContentId = voteContentId;
        this.content = content;
        this.count = count;
    }
}
