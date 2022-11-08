package SW_Engineering.Group3.dto.workflow;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterPhaseDto {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private LocalDate finishDate;

}
