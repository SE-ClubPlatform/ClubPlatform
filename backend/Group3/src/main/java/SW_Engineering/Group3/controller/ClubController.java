package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.domain.auth.Authority;
import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.dto.MainResult;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.club.ClubSimpleInfoDto;
import SW_Engineering.Group3.dto.club.ClubRegisterDto;
import SW_Engineering.Group3.dto.MainPage.UnjoinClubDto;
import SW_Engineering.Group3.dto.club.DealUserSignupRequestDto;
import SW_Engineering.Group3.dto.club.JoinMemberDto;
import SW_Engineering.Group3.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final Response response;
    private final ClubService clubService;
    private final NoticeService noticeService;
    private final MemberService memberService;

    /**
     * 모든 동아리 조회
     */
    @GetMapping("/all")
    public MainResult getAllClubs() {
        List<UnjoinClubDto> allClubs = clubService.getAllClubs().stream()
                .map(club -> new UnjoinClubDto(club.getId(), club.getClubName(), club.getCategory()))
                .collect(Collectors.toList());

        return new MainResult(allClubs.size(), allClubs);
    }


    /**
     * 동아리 등록
     */
    @PostMapping
    public ResponseEntity registerClub(@Validated @RequestBody ClubRegisterDto clubRegisterDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return response.fail("입력 정보가 올바른지 확인해 주세요", HttpStatus.BAD_REQUEST);
        }

        Long clubId = clubService.registerClub(clubRegisterDto);

        if (clubId == null)
            return response.success(clubId, "올바르지 않은 인증토큰을 입력하셨습니다.", HttpStatus.OK);
        else
            return response.success(clubId, "동아리 등록에 성공했습니다.", HttpStatus.OK);
    }

    /**
     * 동아리 메인페이지
     */
    @GetMapping("/{club_id}/mainpage")
    public ResponseEntity<?> getClubMainPageInfo(Principal principal, @PathVariable("club_id") Long clubId) throws IOException {

        Long memberId = Long.parseLong(principal.getName());

        if (clubService.checkUserClubAuthority(memberId, clubId, Authority.ROLE_MEMBER)) {
            ClubSimpleInfoDto clubSimpleInfoDto = clubService.getClubInfo(clubId);
            return response.success(clubSimpleInfoDto);
        }

        return response.fail("접근 권한이 없습니다.", HttpStatus.FORBIDDEN);
    }

    /**
     * 유저의 동아리 가입 신청
     */
    @PostMapping("/{club_id}/application")
    public ResponseEntity signUpForClub(Principal principal, @PathVariable("club_id") Long clubId) {
        try {
            Long memberId = Long.parseLong(principal.getName());

            return clubService.signUpForClub(memberId, clubId);
        } catch (NullPointerException e) {
            return response.fail("유저 정보가 없습니다", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 동아리 가입 신청 승인
     */
    @PostMapping("/{club_id}/application-members")
    public ResponseEntity allowUserSignupRequest(Principal principal, @PathVariable("club_id") Long clubId,
                                                 @RequestBody DealUserSignupRequestDto dto) {
        try {
            Long memberId = Long.parseLong(principal.getName());

            if (clubService.checkUserClubAuthority(memberId, clubId, Authority.ROLE_PRESIDENT)) {
                if (dto.getStatus().equals("approve"))
                    return clubService.dealUserRequest(clubId, dto.getStudentId(), true);

                return clubService.dealUserRequest(clubId, dto.getStudentId(), false);
            }

            return response.fail("접근 권한이 없습니다.", HttpStatus.FORBIDDEN);

        } catch (NullPointerException e) {
            return response.fail("유저 정보가 없습니다", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 동아리 가입 신청 목록 조회
     */
    @GetMapping("/{club_id}/application-members")
    public ResponseEntity showAllSignupRequest(Principal principal, @PathVariable("club_id") Long clubId) {
        try {

            Long memberId = Long.parseLong(principal.getName());

            if (clubService.checkUserClubAuthority(memberId, clubId, Authority.ROLE_PRESIDENT)) {
                return clubService.showAllApplicationMember(clubId);
            }

            return response.fail("접근 권한이 없습니다.", HttpStatus.FORBIDDEN);

        } catch (NullPointerException e) {
            return response.fail("유저 정보가 없습니다", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 동아리 멤버 조회
     */
    @GetMapping("/{club_id}/members")
    public ResponseEntity<?> viewClubMembers(Principal principal, @PathVariable("club_id") Long clubId) {

        Long memberId = Long.parseLong(principal.getName());

        if (clubService.checkUserClubAuthority(memberId, clubId, Authority.ROLE_MANAGER)) {
            MainResult mainResult = clubService.viewClubMembers(clubId);

            return response.success(mainResult);
        }

        return response.fail("조회 권한이 없거나 존재하지 않는 유저입니다.", HttpStatus.BAD_REQUEST);
    }

    /* 이미지 저장
    @PostMapping("/{club_id}/image")
    public String saveClubImage(MultipartFile image, @PathVariable("club_id") Long clubId) throws IOException {

        Club club = clubService.findClubById(clubId);

        fileService.saveClubImage(club, image);

        return fileService.getClubImage(club);
    }
    */


    @GetMapping("/{club_id}/mainpage-board")
    public ResponseEntity<?> getSimpleBoardInfo(@PathVariable("club_id") Long clubId) {
        return response.success(noticeService.getSimpleNoticeList(clubId));
    }

    @DeleteMapping("/{club_id}/member/{member_id}")
    public ResponseEntity<?> deleteMember(Principal principal, @PathVariable("club_id") Long clubId,
                                          @PathVariable("member_id") Long deleteMemberId) {
        Long memberId = Long.parseLong(principal.getName());

        System.out.println(memberId);

        if (clubService.checkUserClubAuthority(memberId, clubId, Authority.ROLE_PRESIDENT)) {
            clubService.deleteClubMember(clubId, deleteMemberId);
            return response.success(noticeService.getSimpleNoticeList(clubId), "성공적으로 삭제했습니다", HttpStatus.OK);
        }

        return response.fail("조회 권한이 없거나 존재하지 않는 유저입니다.", HttpStatus.FORBIDDEN);
    }


    /**
     * 멤버 상세 조회
     */
    @GetMapping("/{club_id}/members/{student_id}")
    public JoinMemberDto viewClubMemberDetail(Principal principal, @PathVariable("club_id") Long clubId, @PathVariable("student_id") String targetStudentId) {
        Member member = memberService.findMemberByStudentId(targetStudentId);

        return JoinMemberDto.createJoinMemberDto(member);
    }

    /**
     * 멤버 권한 변경
     */
    @GetMapping("/{club_id}/members/{student_id}/changeAuthority")
    public ResponseEntity changeClubMemberDetail(Principal principal, @PathVariable("club_id") Long clubId, @PathVariable("student_id") String targetStudentId) {
        Long memberId = 1L;
        Member member = memberService.findMemberById(memberId);

        if (member.getAuthority().getRank() != 4) {
            return response.fail("부여 권한이 없는 사용자 입니다.", HttpStatus.BAD_REQUEST);
        }

        if (!clubService.changeAuthority(memberId, targetStudentId, clubId)) {
            return response.fail("부여에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }

        return response.success();
    }

    /**
     * 멤버 명단 삭제
     */
    @DeleteMapping("{club_id}/members/{student_id}")
    public ResponseEntity deleteNotice(Principal principal,  @PathVariable("club_id") Long clubId, @PathVariable("student_id") String targetStudentId) {
        if (!(clubService.deleteMember(targetStudentId, clubId))) {
            return response.fail("삭제에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }

        return response.success();
    }
}
