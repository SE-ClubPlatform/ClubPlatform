package SW_Engineering.Group3.dto.workflow;

import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.workflow.Work;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterWorkDto {

    @NotNull
    private String title; // 활동 제목

    @NotNull
    private String introduce; // 개괄적인 활동 설명

    @NotNull
    private LocalDate finishDate; // 업무 수행 기간

    /**
     *  Author은 Session Or Token 도입 후 삭제 예정
     */
    private String author; // 작성자 이름

    public Work toWork(Club club) {

        return Work.builder()
                .club(club)
                .title(this.title)
                .introduce(this.introduce)
                .finishDate(this.finishDate)
                .build();

    }

}
