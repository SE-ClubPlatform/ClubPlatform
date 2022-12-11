package SW_Engineering.Group3.dto.workflow;

import SW_Engineering.Group3.domain.workflow.VoteContent;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VoteContentDto {

    private String content;
    private int count;

    public VoteContentDto(String content, int count) {
        this.content = content;
        this.count = count;
    }
}
