package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.workflow.Phase;
import SW_Engineering.Group3.domain.workflow.Work;
import SW_Engineering.Group3.dto.MainResult;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.workflow.DetailWorkDto;
import SW_Engineering.Group3.dto.workflow.RegisterPhaseDto;
import SW_Engineering.Group3.dto.workflow.RegisterWorkDto;
import SW_Engineering.Group3.dto.workflow.WorkMainPageDto;
import SW_Engineering.Group3.service.ClubService;
import SW_Engineering.Group3.service.PhaseService;
import SW_Engineering.Group3.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club/{club_id}/work")
public class WorkFlowController {

    private final PhaseService phaseService;
    private final ClubService clubService;
    private final WorkService workService;
    private final Response response;

    /**
     * 활동 저장
     */
    @PostMapping()
    public ResponseEntity registerWork(@Validated @RequestBody RegisterWorkDto registerWorkDto, BindingResult bindingResult,
                                       @PathVariable("club_id") Long clubId){

        if(bindingResult.hasErrors()){
            return response.fail("입력 정보가 올바른지 확인해 주세요", HttpStatus.BAD_REQUEST);
        }

        System.out.println("club_id = {}" + clubId);

        Club club = clubService.findClubById(clubId);

        workService.register(registerWorkDto.toWork(club));

        return response.success("업무 저장에 성공했습니다.");

    }

    /**
     * 메인페이지에 사용되는 활동 기본 정보 반환
     */
    @GetMapping("/mainpage")
    public MainResult getClubMainPageWorkInfo(@PathVariable("club_id") Long clubId) {

        Club club = clubService.findClubById(clubId);

        List<Work> works = workService.findAllWorks(club);

        return new MainResult(works.size(), works.stream()
                                            .map(w -> new WorkMainPageDto(w.getId(), w.getTitle()))
                                            .collect(Collectors.toList()));
    }

    /**
     * [활동별 진행상황 보기]에서 반환되는 활동 정보 반환
     */
    @GetMapping("/all")
    public MainResult getClubAllWorks(@PathVariable("club_id") Long clubId) {

        Club club = clubService.findClubById(clubId);

        List<Work> works = workService.findAllWorks(club);

        return new MainResult(works.size(), works.stream()
                .map(w -> DetailWorkDto.builder()
                        .title(w.getTitle())
                        .introduce(w.getIntroduce())
                        .phaseStep(w.getCurrentStep())
                        .build())
                .collect(Collectors.toList()));
    }

    /**
     * 활동 단계 정보 등록
     */
    @PostMapping("/{work_id}/phase")
    public ResponseEntity registerPhase(@Validated @RequestBody RegisterPhaseDto registerPhaseDto, BindingResult bindingResult,
                              @PathVariable("club_id") Long clubId, @PathVariable("work_id") Long workId) {

        if(bindingResult.hasErrors()){
            return response.fail("입력 정보가 올바른지 확인해 주세요", HttpStatus.BAD_REQUEST);
        }

        Club club = clubService.findClubById(clubId);
        Work work = workService.findWorkById(club, workId);

        try {
            phaseService.savePhase(work,
                    Phase.builder()
                            .work(work)
                            .clubId(clubId)
                            .title(registerPhaseDto.getTitle())
                            .content(registerPhaseDto.getContent())
                            .finishDate(registerPhaseDto.getFinishDate())
                            .step(work.getCurrentStep() + 1)
                            .build());
        } catch(IllegalArgumentException e) {
            return response.fail("더 이상 단계 정보를 등록하실 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        return response.success(work.getCurrentStep() + "단계 정보를 성공적으로 저장했습니다");

    }

}
