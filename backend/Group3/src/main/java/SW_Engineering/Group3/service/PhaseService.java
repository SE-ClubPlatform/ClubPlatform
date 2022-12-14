package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.workflow.Phase;
import SW_Engineering.Group3.domain.workflow.Vote;
import SW_Engineering.Group3.domain.workflow.VoteContent;
import SW_Engineering.Group3.domain.workflow.Work;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.workflow.RegisterPhaseDto;
import SW_Engineering.Group3.repository.workflow.PhaseRepository;
import SW_Engineering.Group3.repository.workflow.VoteContentRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PhaseService {

    private final Response response;
    private final PhaseRepository phaseRepository;
    private final VoteContentRepository voteContentRepository;

    /**
     * 단계 등록
     */
    @Transactional
    public ResponseEntity<?> savePhase(Map<String, Object> map, Club club, Work work) {

        RegisterPhaseDto registerPhaseDto = parseJson(map);

        try {
            Phase savePhase = Phase.builder()
                    .work(work)
                    .clubId(club.getId())
                    .title(registerPhaseDto.getTitle())
                    .content(registerPhaseDto.getContent())
                    .finishDate(registerPhaseDto.getFinishDate().toString())
                    .step(work.getCurrentStep() + 1)
                    .voteActivate(registerPhaseDto.isVoteActivate())
                    .vote(registerPhaseDto.getVote())
                    .build();

            if(savePhase.getStep() > 5) {
                return response.fail("더 이상 단계 정보를 등록하실 수 없습니다.", HttpStatus.BAD_REQUEST);
            }
            phaseRepository.save(savePhase);

            if(registerPhaseDto.getVote() != null) {
                registerPhaseDto.getVote().setPhase(savePhase);
            }

            work.updateStep();
            return response.success(savePhase.getId(), work.getCurrentStep() + "단계 정보를 성공적으로 저장했습니다", HttpStatus.OK);

        } catch(IllegalArgumentException e) {
            return response.fail("더 이상 단계 정보를 등록하실 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

    }

    public RegisterPhaseDto parseJson(Map<String, Object> map) {
        Gson gson = new Gson(); // 구글의 json parser 라이브러리

        // Json Parsing
        //(1) phase
        String title = ((String) map.get("title"));
        String content = ((String) map.get("content"));
        String finishDate = (String) map.get("finishDate");
        boolean voteActivate = ((boolean) map.get("voteActivate"));

        //(2) vote
        ArrayList voteJsonArray = (ArrayList) map.get("vote");

        if(voteJsonArray != null) {
            Map<String, ?> temp = (Map) voteJsonArray.get(0);

            String voteTitle = (String) temp.get("title");
            LocalDate voteFinishDate = LocalDate.parse((String) temp.get("finishDate"));
            Vote vote = new Vote(voteTitle, voteFinishDate);

            //(3) voteContent
            ArrayList voteContentJsonArray = (ArrayList) temp.get("contents");

            List<VoteContent> voteContents = new ArrayList<>();
            for (int i = 0; i < voteContentJsonArray.size(); i++) {
                temp = (Map) voteContentJsonArray.get(i);

                VoteContent voteContent = new VoteContent((String) temp.get("content"));
                vote.addVoteContent(voteContent);
            }

            return new RegisterPhaseDto(title, content, finishDate, voteActivate, vote);
        }

        return new RegisterPhaseDto(title, content, finishDate, voteActivate, null);
    }

    /**
     * 특정 단계의 투표 가능 여부 조사
     */
    public boolean showPhaseVoteActivate(Club club, Work work, int step) {

        Phase phase = phaseRepository.findPhaseByClubAndWork(club.getId(), work, step);

        return phase.isVoteActivate();
    }

    /**
     * 동아리 번호, 활동 번호, 단계 스텝으로 특정 투표 조회
     */
    public Vote getVoteInfo(Club club, Work work, int step) {
        Phase phase = phaseRepository.findPhaseByClubAndWork(club.getId(), work, step);

        return phase.getVote();
    }

    /**
     * 투표 컨텐츠의 투표 카운트 수를 올림
     */
    @Transactional
    public ResponseEntity<?> addContentCount(Long voteContentId) {
        VoteContent voteContent = voteContentRepository.findById(voteContentId).orElse(null);

        if(voteContent == null) {
            return response.fail("존재하지 않는 투표 내용입니다.", HttpStatus.BAD_REQUEST);
        }

        voteContent.addCount();

        return response.success("현재 투표 수", String.valueOf(voteContent.getCount()), HttpStatus.OK);
    }

}
