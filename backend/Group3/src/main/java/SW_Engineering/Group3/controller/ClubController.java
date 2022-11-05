package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.dto.MainResult;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.club.ClubMainPageDto;
import SW_Engineering.Group3.dto.club.ClubRegisterDto;
import SW_Engineering.Group3.dto.club.MainPageDto;
import SW_Engineering.Group3.service.ClubService;
import io.swagger.annotations.*;
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
@RequestMapping("/club")
public class ClubController {

    private final Response response;
    private final ClubService clubService;

    @ApiOperation(
            value = "[동아리 정보가 궁금하다면?]에 노출될 모든 동아리 정보 반환"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "동아리 이름, 카테고리가 표시된 모든 동아리 정보 반환")
    })
    @GetMapping("/all")
    public MainResult getAllClubs(){
        List<MainPageDto> allClubs =  clubService.getAllClubs().stream()
                .map(club -> new MainPageDto(club.getClubName(), club.getCategory()))
                .collect(Collectors.toList());

        return new MainResult(allClubs.size(), allClubs);
    }

    @ApiOperation(
            value = "동아리 등록",
            notes = "동아리 회장만 등록할 수 있으며, 등록 시 미리 전달된 인증코드를 입력해야함"
    )
    @ApiImplicitParam(
            name = "clubRegisterDto",
            value = "동아리 이름, 회장 이름, 카테고리, 한 줄 소개, 동아리 인증 코드 정보가 포함된 동아리 등록 FORM"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "동아리 등록 성공"),
            @ApiResponse(code = 400, message = "올바르지 않은 인증코드 입력\nFORM 정보를 모두 입력하지 않음")
    })
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

    @ApiOperation(
            value = "동아리 메인 화면에 노출되는 동아리 정보"
    )
    @ApiImplicitParam(
            name = "clubId",
            type = "Long",
            value = "유저가 접속한 동아리 번호"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "동아리 이름, 회장 이름, 동아리원 수, 카테고리, 한 줄 소개 정보 반환"),
            @ApiResponse(code = 400, message = "올바르지 않은 동아리 번호")
    })
    @GetMapping("/{club_id}/mainpage")
    public ClubMainPageDto getClubMainPageInfo(@PathVariable("club_id") Long clubId){

        ClubMainPageDto clubMainPageDto = clubService.getClubInfo(clubId);

        return clubMainPageDto;
    }

    @ApiOperation(
            value = "유저의 동아리 가입",
            notes = "메인페이지에서 가입할 수 있음"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "club_id",
                    type = "Long",
                    value = "가입하고자 하는 동아리 번호"
            ),
            @ApiImplicitParam(
                    name = "member_id",
                    type = "Long",
                    value = "등록을 원하는 유저 번호"
            )
        }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저의 동아리 가입 성공"),
            @ApiResponse(code = 400, message = "올바르지 않은 동아리/유저 번호")
    })
    @PostMapping("/{club_id}/registration/{member_id}")
    public ResponseEntity registerUserToClub(@PathVariable("club_id") Long clubId, @PathVariable("member_id") Long memberId){
        return clubService.registerUser(clubId, memberId);
    }

    @ApiOperation(
            value = "모든 동아리원 조회"
    )
    @ApiImplicitParam(
            name = "clubId",
            type = "Long",
            value = "조회하고자 하는 동아리 번호"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "동아리원 정보(이름, 학번, 학과, 전화번호)"),
            @ApiResponse(code = 400, message = "올바르지 않은 동아리 번호")
    })
    @GetMapping("/{clubId}/members")
    public MainResult viewClubMembers(@PathVariable Long clubId){
        return clubService.viewClubMembers(clubId);
    }

}
