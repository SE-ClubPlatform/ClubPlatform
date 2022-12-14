package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.clubroom.ClubRoomLogDto;
import SW_Engineering.Group3.service.ClubRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/clubRoom")
@RequiredArgsConstructor
public class ClubRoomController {

    private final Response response;
    private final ClubRoomService clubRoomService;

    /**
     * 동아리방 출입 로그 생성
     */
    @GetMapping("/{club_id}/toggle")
    public ResponseEntity<?> toggleMemberRecord(Principal principal, @PathVariable(value = "club_id") Long clubId) {
        try {
            Long memberId = Long.parseLong(principal.getName());

            if (!clubRoomService.toggleMemberRecord(memberId, clubId)) {
                return response.fail("올바르지 않은 유저 혹은 동아리 정보입니다.", HttpStatus.BAD_REQUEST);
            }

            return response.success("성공적으로 반영되었습니다.");
        } catch(NullPointerException e) {
            return response.fail("유저 정보가 없습니다", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 동아리방 상위 10개 출입 로그 반환
     */
    @GetMapping("/{club_id}")
    public ResponseEntity<?> getClubRoomLogs(@PathVariable(value = "club_id") Long clubId) {
        List<ClubRoomLogDto> dtos = clubRoomService.getClubRoomLogs(clubId);

        if(dtos == null) {
            return response.fail("올바르지 않은 동아리 번호입니다.", HttpStatus.BAD_REQUEST);
        }

        return response.success(dtos);
    }
}
