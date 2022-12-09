package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.workflow.Phase;
import SW_Engineering.Group3.domain.workflow.Work;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.workflow.RegisterPhaseDto;
import SW_Engineering.Group3.repository.workflow.PhaseRepository;
import SW_Engineering.Group3.repository.workflow.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PhaseService {

    private final Response response;
    private final PhaseRepository phaseRepository;

    /*
    @Transactional
    public void makeDefaultPhases(Work work, Long clubId) {

        for(int i = 1 ; i <= 5 ; i++) {
            Phase newPhase = Phase.builder()
                    .work(work)
                    .clubId(clubId)
                    .step(i)
                    .build();

            phaseRepository.save(newPhase);
        }

    }
    */

    /**
     * 단계 등록
     */
    @Transactional
    public ResponseEntity<?> savePhase(RegisterPhaseDto registerPhaseDto, Club club, Work work) {
        try {
            phaseRepository.save(
                    Phase.builder()
                            .work(work)
                            .clubId(club.getId())
                            .title(registerPhaseDto.getTitle())
                            .content(registerPhaseDto.getContent())
                            .finishDate(registerPhaseDto.getFinishDate())
                            .step(work.getCurrentStep() + 1)
                            .voteActivate(registerPhaseDto.isVoteActivate())
                            .build());
        } catch(IllegalArgumentException e) {
            return response.fail("더 이상 단계 정보를 등록하실 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        work.updateStep();
        return response.success(work.getCurrentStep() + "단계 정보를 성공적으로 저장했습니다");

    }

    /**
     * 특정 단계의 투표 가능 여부 조사
     */
    public boolean showPhaseVoteActivate(Club club, Work work, int step) {

        Phase phase = phaseRepository.findPhaseByClubAndWorkId(club.getId(), work, step);

        return phase.isVoteActivate();
    }

}
