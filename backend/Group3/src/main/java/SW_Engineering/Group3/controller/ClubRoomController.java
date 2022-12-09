package SW_Engineering.Group3.controller;

import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.service.ClubRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/clubRoom")
@RequiredArgsConstructor
public class ClubRoomController {

    private final Response response;
    private final ClubRoomService clubRoomService;

    @PutMapping("/{club_id}")
    public ResponseEntity<?> toggleMemberRecord(Principal principal, @PathVariable(value = "club_id") Long clubId) {
        Long memberId = Long.parseLong(principal.getName());

        if(!clubRoomService.toggleMemberRecord(memberId, clubId)) {
            return response.fail("올바르지 않은 유저 정보입니다.", HttpStatus.BAD_REQUEST);
        }

        return response.success("성공적으로 반영되었습니다.");
    }
}
