package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.club.ClubMainPageDto;
import SW_Engineering.Group3.dto.club.ClubRegisterDto;
import SW_Engineering.Group3.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final Response response;
    private final ClubService clubService;

    @PostMapping
    public ResponseEntity registerClub(@Validated @RequestBody ClubRegisterDto clubRegisterDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return response.fail("입력 정보가 올바른지 확인해 주세요", HttpStatus.BAD_REQUEST);
        }

        Long clubId = clubService.registerClubInfo(clubRegisterDto);

        if(clubId == null)
            return response.success(clubId, "올바르지 않은 인증토큰을 입력하셨습니다.", HttpStatus.OK);
        else
            return response.success(clubId, "동아리 등록에 성공했습니다.", HttpStatus.OK);
    }

    @GetMapping("/{clubId}/mainpage")
    public ClubMainPageDto getMainPage(@PathVariable Long clubId){

        // 클럽 서비스로부터 전달된 clubID에 해당하는 동아리 정보를 받아옴
        ClubMainPageDto clubMainPageDto = clubService.getClubInfo(clubId);

        return clubMainPageDto;

    }


}
