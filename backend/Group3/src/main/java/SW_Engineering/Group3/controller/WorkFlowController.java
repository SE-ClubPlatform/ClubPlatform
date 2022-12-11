package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.workflow.Vote;
import SW_Engineering.Group3.domain.workflow.VoteContent;
import SW_Engineering.Group3.domain.workflow.Work;
import SW_Engineering.Group3.dto.MainResult;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.workflow.*;
import SW_Engineering.Group3.service.ClubService;
import SW_Engineering.Group3.service.PhaseService;
import SW_Engineering.Group3.service.WorkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/club/{club_id}/work", produces = "application/json; charset=utf8")
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

        Club club = clubService.findClubById(clubId);

        workService.register(registerWorkDto.toWork(club));

        return response.success("업무 저장에 성공했습니다.");

    }

    /**
     * 메인페이지에 사용되는 활동 기본 정보 반환
     */
    @GetMapping()
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
                        .voteActivate(phaseService.showPhaseVoteActivate(club, w, w.getCurrentStep()))
                        .build())
                .collect(Collectors.toList()));
    }

    /**
     * 활동 단계 정보 등록
     */
    @PostMapping("/{work_id}/phase")
    public ResponseEntity<?> registerPhase(@Validated @RequestBody Map<String, Object> map,
                                        @PathVariable("club_id") Long clubId, @PathVariable("work_id") Long workId) throws JsonProcessingException {

        // club, work
        Club club = clubService.findClubById(clubId);
        Work work = workService.findWorkById(club, workId);

        return phaseService.savePhase(map, club, work);
    }

    /**
     * 투표 정보 불러오기 기능
     */
    @GetMapping("/{work_id}/phase/{phase_step}/vote")
    public ResponseEntity<?> getVoteInfo(@PathVariable("club_id") Long clubId, @PathVariable("work_id") Long workId,
                                         @PathVariable("phase_step") int step) {

        // club, work
        Club club = clubService.findClubById(clubId);
        Work work = workService.findWorkById(club, workId);

        Vote findVote = phaseService.getVoteInfo(club, work, step);

        VoteDto voteDto = new VoteDto(findVote.getTitle(), findVote.getFinishDate(),
                findVote.getContents().stream()
                        .map(voteContent -> new VoteContentDto(voteContent.getId(), voteContent.getContent(), voteContent.getCount()))
                        .collect(Collectors.toList()));

        return response.success(voteDto);
    }

    /**
     * 투표 선택 기능 - 각 투표 컨텐츠의 카운트가 올라감
     */
    @PostMapping("{work_id}/vote/{vote_content_id}")
    public ResponseEntity<?> addContentCount(@PathVariable("vote_content_id") Long voteContentId) {
        return phaseService.addContentCount(voteContentId);
    }

}
