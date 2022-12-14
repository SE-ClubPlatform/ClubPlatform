package SW_Engineering.Group3.dto.workflow;

import SW_Engineering.Group3.domain.workflow.Vote;
import lombok.AccessLevel;
import lombok.Builder;
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
    private String finishDate;

    @NotNull
    private boolean voteActivate;

    private Vote vote;

    @Builder
    public RegisterPhaseDto(String title, String content, String finishDate,
                                              boolean voteActivate, Vote vote) {
        this.title = title;
        this.content = content;
        this.finishDate = finishDate;
        this.voteActivate = voteActivate;
        this.vote = vote;
    }
}
