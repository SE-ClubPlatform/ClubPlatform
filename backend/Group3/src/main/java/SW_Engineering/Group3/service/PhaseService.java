package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.workflow.Phase;
import SW_Engineering.Group3.domain.workflow.Work;
import SW_Engineering.Group3.repository.workflow.PhaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PhaseService {

    private final PhaseRepository phaseRepository;

    @Transactional
    public void makeDefaultPhases(Work work, Long clubId) {

        for(int i = 1 ; i <= 5 ; i++) {
            Phase newPhase = Phase.builder()
                    .work(work)
                    .clubId(clubId)
                    .currentStep(i)
                    .isFinish(false)
                    .build();

            phaseRepository.save(newPhase);
        }

    }
}
