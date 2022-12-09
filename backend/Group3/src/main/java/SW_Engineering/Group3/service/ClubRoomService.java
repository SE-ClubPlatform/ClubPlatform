package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.clubroom.ClubRoom;
import SW_Engineering.Group3.domain.clubroom.ClubRoomLog;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.repository.ClubRoom.ClubRoomLogRepository;
import SW_Engineering.Group3.repository.ClubRoom.ClubRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubRoomService {

    private final MemberService memberService;
    private final ClubRoomLogRepository clubRoomLogRepository;
    private final ClubRoomRepository clubRoomRepository;


    /**
     * 동아리방 출입 기록 생성
     */
    @Transactional
    public boolean toggleMemberRecord(Long memberId, Long clubId) {

        //1. 동아리 & 유저 정보 가져옴
        ClubRoom clubRoom = clubRoomRepository.findByClubId(clubId);
        Member member = memberService.findMemberById(memberId);

        if(member == null || clubRoom == null) {
            return false;
        }

        //2. 해당 유저의 가장 최근 동아리방 출입 기록을 가져옴
        List<String> record = clubRoomLogRepository.findMemberEnterType(clubRoom, member, PageRequest.of(0,1));

        //3. 기록이 없거나, 가장 최근 활동이 퇴장이면 입장 기록을 새로 만듬
        if(record.size() == 0 || record.get(0).equals("퇴장")) {
            clubRoomLogRepository.save(ClubRoomLog.builder()
                            .clubRoom(clubRoom)
                            .member(member)
                            .localDateTime(LocalDateTime.now())
                            .type("입장")
                            .build());
        } else { // 4. 가장 최근 활동이 입장이면 퇴장 기록을 만듬
            clubRoomLogRepository.save(ClubRoomLog.builder()
                    .clubRoom(clubRoom)
                    .member(member)
                    .localDateTime(LocalDateTime.now())
                    .type("퇴장")
                    .build());
        }

        return true;
    }
}
