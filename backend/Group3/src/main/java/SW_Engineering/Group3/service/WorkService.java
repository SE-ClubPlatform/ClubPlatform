package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.workflow.Work;
import SW_Engineering.Group3.repository.workflow.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkService {

    private final PhaseService phaseService;
    private final WorkRepository workRepository;

    /**
     * 활동 등록 기능
     */
    @Transactional
    public void register(Work work) {

        //1. 업무 저장
        Work savedWork = workRepository.save(work);

        //2. 해당 업무와 연결되는 5가지 단계 생성
        //phaseService.makeDefaultPhases(savedWork, work.getClub().getId());

    }

    /**
     * 전달받은 동아리 가진 모든 활동을 반환
     */
    public List<Work> findAllWorks(Club club) {
        return workRepository.findWorksByClub(club);
    }

    /**
     * 전달받은 동아리가 가진 특정 활동 반환
     */
    public Work findWorkById(Club club, Long workId) {
        return workRepository.findWorkByClubAndWorkId(club, workId);
    }
}
