package SW_Engineering.Group3.dto.workflow;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkMainPageDto {

    private Long id;
    private String title;

    public WorkMainPageDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
