package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.workflow.Work;
import SW_Engineering.Group3.repository.workflow.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkService {

    private final PhaseService phaseService;
    private final WorkRepository workRepository;

    @Transactional
    public void register(Work work) {

        //1. 업무 저장
        Work savedWork = workRepository.save(work);

        //2. 해당 업무와 연결되는 5가지 단계 생성
        phaseService.makeDefaultPhases(savedWork, work.getClub().getId());

    }
}
