package SW_Engineering.Group3.dto.workflow;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VoteDto {

    private String title;
    private String finishDate;
    private List<VoteContentDto> contents;

    public VoteDto(String title, LocalDate finishDate, List<VoteContentDto> contents) {
        this.title = title;
        this.finishDate = finishDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.contents = contents;
    }
}
