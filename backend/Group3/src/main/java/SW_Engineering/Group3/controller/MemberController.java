package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.club.ClubMemberList;
import SW_Engineering.Group3.dto.MainPage.UnjoinClubDto;
import SW_Engineering.Group3.dto.MainResult;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.MainPage.JoinClubDto;
import SW_Engineering.Group3.service.FileService;
import SW_Engineering.Group3.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

    private final Response response;
    private final MemberService memberService;
    private final FileService fileService;

    /**
     * 유저가 속한 모든 동아리 조회
     */
    @GetMapping("/join-clubs")
    public ResponseEntity<?> getJoinClubs(Principal principal){
        try {
            Long memberId = Long.parseLong(principal.getName());

            List<ClubMemberList> userJoinClubs = memberService.getJoinClubs(memberId);

            List<JoinClubDto> dtos = userJoinClubs.stream()
                    .map(ClubMemberList::getClub)
                    .map(club -> {
                        try {
                            return new JoinClubDto(club.getId(), club.getClubName(), fileService.getClubImage(club));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());

            return response.success(new MainResult(dtos.size(), dtos));

        } catch(NullPointerException e) {
            return response.fail("유저 정보가 없습니다", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 유저가 가입하지 않은 동아리 중 랜덤한 동아리 3개 반환
     */
    @GetMapping("/random-clubs")
    public ResponseEntity<?> getRandomClubs(Principal principal) {
        try {
            Long memberId = Long.parseLong(principal.getName());

            List<UnjoinClubDto> dtos = memberService.getRandomClubs(memberId).stream()
                    .map(c -> new UnjoinClubDto(c.getId(), c.getClubName(), c.getCategory()))
                    .collect(Collectors.toList());

            return response.success(new MainResult<>(dtos.size(), dtos));

        } catch(NullPointerException e) {
            return response.fail("유저 정보가 없습니다", HttpStatus.BAD_REQUEST);
        }
    }

}
