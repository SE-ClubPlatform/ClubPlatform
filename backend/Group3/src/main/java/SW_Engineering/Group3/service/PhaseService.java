package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.workflow.Phase;
import SW_Engineering.Group3.domain.workflow.Work;
import SW_Engineering.Group3.dto.Response;
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
    public void savePhase(Work work, Phase phase) {

        if(phase.getStep() > 5) {
            throw new IllegalArgumentException("더 이상 단계를 등록하실 수 없습니다.");
        }

        //1. 단계 저장
        phaseRepository.save(phase);

        //2. 활동에 단계 정보 저장
        work.addPhase(phase);
        work.updateStep();

    }

}
