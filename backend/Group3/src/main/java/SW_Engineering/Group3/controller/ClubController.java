package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.domain.auth.Authority;
import SW_Engineering.Group3.dto.MainResult;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.club.ClubMainPageDto;
import SW_Engineering.Group3.dto.club.ClubRegisterDto;
import SW_Engineering.Group3.dto.MainPage.UnjoinClubDto;
import SW_Engineering.Group3.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final Response response;
    private final ClubService clubService;

    /**
     * 모든 동아리 조회
     */
    @GetMapping("/all")
    public MainResult getAllClubs(){
        List<UnjoinClubDto> allClubs =  clubService.getAllClubs().stream()
                .map(club -> new UnjoinClubDto(club.getClubName(), club.getCategory()))
                .collect(Collectors.toList());

        return new MainResult(allClubs.size(), allClubs);
    }


    /**
     * 동아리 등록
     */
    @PostMapping
    public ResponseEntity registerClub(@Validated @RequestBody ClubRegisterDto clubRegisterDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return response.fail("입력 정보가 올바른지 확인해 주세요", HttpStatus.BAD_REQUEST);
        }

        Long clubId = clubService.registerClub(clubRegisterDto);

        if(clubId == null)
            return response.success(clubId, "올바르지 않은 인증토큰을 입력하셨습니다.", HttpStatus.OK);
        else
            return response.success(clubId, "동아리 등록에 성공했습니다.", HttpStatus.OK);
    }

    /**
     * 동아리 메인페이지
     */
    @GetMapping("/{club_id}/mainpage")
    public ClubMainPageDto getClubMainPageInfo(@PathVariable("club_id") Long clubId){

        ClubMainPageDto clubMainPageDto = clubService.getClubInfo(clubId);

        return clubMainPageDto;
    }

    /**
     * 유저의 동아리 가입
     */
    @PostMapping("/{club_id}/registration")
    public ResponseEntity registerUserToClub(@PathVariable("club_id") Long clubId, Principal principal){
        try {
            Long memberId = Long.parseLong(principal.getName());

            return clubService.registerUser(clubId, memberId);
        } catch(NullPointerException e) {
            return response.fail("유저 정보가 없습니다", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 동아리 멤버 조회
     */
    @GetMapping("/{clubId}/members")
    public ResponseEntity<?> viewClubMembers(Principal principal, @PathVariable Long clubId){
        /*
        Long memberId = Long.parseLong(principal.getName());

        if(clubService.checkUserClubAuthority(memberId, clubId, Authority.ROLE_MANAGER)) {
            MainResult mainResult = clubService.viewClubMembers(clubId);

            return response.success(mainResult);
        }
        */

        MainResult mainResult = clubService.viewClubMembers(clubId);

        return response.success(mainResult);

        //return response.fail("조회 권한이 없거나 존재하지 않는 유저입니다.", HttpStatus.BAD_REQUEST);
    }

}
