package SW_Engineering.Group3.dto.clubroom;

import SW_Engineering.Group3.domain.clubroom.ClubRoomLog;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubRoomLogDto {

    private Long clubRoomId;
    private String time;
    private String studentId;
    private String name;
    private String type;

    public static ClubRoomLogDto createClubRoomLogsDto(ClubRoomLog clubRoomLog) {
        ClubRoomLogDto clubRoomLogsDto = new ClubRoomLogDto();

        clubRoomLogsDto.setClubRoomId(clubRoomLog.getId());
        clubRoomLogsDto.setTime(clubRoomLog.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        clubRoomLogsDto.setStudentId(clubRoomLog.getMember().getStudentId());
        clubRoomLogsDto.setName(clubRoomLog.getMember().getUserName());
        clubRoomLogsDto.setType(clubRoomLog.getType());

        return clubRoomLogsDto;
    }
}
