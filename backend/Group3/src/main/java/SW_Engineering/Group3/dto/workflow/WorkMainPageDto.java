package SW_Engineering.Group3.dto.workflow;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkMainPageDto {

    private Long workId; // 활동 번호
    private String title; // 활동 제목

    public WorkMainPageDto(Long workId, String title) {
        this.workId = workId;
        this.title = title;
    }
}
