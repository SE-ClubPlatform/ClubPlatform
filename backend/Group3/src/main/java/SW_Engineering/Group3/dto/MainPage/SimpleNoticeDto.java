package SW_Engineering.Group3.dto.MainPage;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SimpleNoticeDto {

    private Long noticeId;
    private String title;

    @Builder
    public SimpleNoticeDto(Long noticeId, String title) {
        this.noticeId = noticeId;
        this.title = title;
    }
}
