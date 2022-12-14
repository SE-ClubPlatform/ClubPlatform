package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.clubroom.ClubRoom;
import SW_Engineering.Group3.domain.clubroom.ClubRoomLog;
import SW_Engineering.Group3.dto.clubroom.ClubRoomLogDto;
import SW_Engineering.Group3.repository.ClubRoom.ClubRoomLogRepository;
import SW_Engineering.Group3.repository.ClubRoom.ClubRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubRoomService {

    private final MemberService memberService;
    private final ClubService clubService;
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

            clubRoom.addCount();

        } else { // 4. 가장 최근 활동이 입장이면 퇴장 기록을 만듬
            clubRoomLogRepository.save(ClubRoomLog.builder()
                    .clubRoom(clubRoom)
                    .member(member)
                    .localDateTime(LocalDateTime.now())
                    .type("퇴장")
                    .build());

            clubRoom.minusCount();
        }

        return true;
    }

    /**
     * 동아리방 모든 출입기록 가져오기
     */
    public List<ClubRoomLogDto> getClubRoomLogs(Long clubId) {

        //1. 동아리 정보 가져옴
        ClubRoom clubRoom = clubRoomRepository.findByClubId(clubId);

        if(clubRoom == null) {
            return null;
        }

        //2. limit을 위한 pageable 정의
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(0, 10, sort);

        //3. 상위 10개 로그를 dto로 변환 후 반환
        return clubRoomLogRepository.findAll(pageable).stream()
                .map(c -> ClubRoomLogDto.createClubRoomLogsDto(c))
                .collect(Collectors.toList());

    }

    /**
     * 동아리 정보를 이용해 동아리방 정보를 얻어온다
     */
    public ClubRoom getClubRoomByClub(Long clubId) {
        Club club = clubService.findClubById(clubId);

        return club.getClubRoom();
    }

}
