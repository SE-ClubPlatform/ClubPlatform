package SW_Engineering.Group3.dto.workflow;

import SW_Engineering.Group3.domain.workflow.PhaseName;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailWorkDto {

    private String title; // 활동 제목
    private String introduce; // 개괄적인 활동 설명
    private PhaseName phaseName; // 현재 활동 단계 이름
    private int phaseStep; // 현재 활동 단계

    private int noticeId; // 단계와 연결되는 공지사항 번호

    private boolean voteActivate; // 해당 단계가 투표를 받는지 여부

    @Builder
    public DetailWorkDto(String title, String introduce, int phaseStep, boolean voteActivate) {
        this.title = title;
        this.introduce = introduce;
        this.phaseStep = phaseStep;
        this.phaseName = PhaseName.valueOf(Arrays.asList(PhaseName.values()).get(phaseStep - 1).name());
        this.voteActivate = voteActivate;
    }

}
