package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.workflow.RegisterWorkDto;
import SW_Engineering.Group3.service.ClubService;
import SW_Engineering.Group3.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club/{club_id}/work")
public class WorkFlowController {

    private final ClubService clubService;
    private final WorkService workService;
    private final Response response;

    @PostMapping()
    public ResponseEntity registerWork(@Validated @RequestBody RegisterWorkDto registerWorkDto, BindingResult bindingResult,
                                       @PathVariable("club_id") Long clubId){

        if(bindingResult.hasErrors()){
            return response.fail("입력 정보가 올바른지 확인해 주세요", HttpStatus.BAD_REQUEST);
        }

        Club club = clubService.findClubById(clubId);

        if(club == null) {
            return response.fail("존재하지 않는 동아리입니다.", HttpStatus.BAD_REQUEST);
        }

        workService.register(registerWorkDto.toWork(club));

        return response.success("업무 저장에 성공했습니다.");

    }

}
