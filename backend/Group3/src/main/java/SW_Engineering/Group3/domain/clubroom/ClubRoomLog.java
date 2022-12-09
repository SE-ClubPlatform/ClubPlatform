package SW_Engineering.Group3.domain.clubroom;

import SW_Engineering.Group3.domain.auth.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubRoomLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private ClubRoom clubRoom; // 연결된 동아리방

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 출입한 동아리원

    @Column
    private LocalDateTime time; //출입 시간

    @Column
    private String type; // 출입 타입(입장/퇴장)

    @Builder
    public ClubRoomLog(ClubRoom clubRoom, Member member, LocalDateTime localDateTime, String type) {
        this.clubRoom = clubRoom;
        this.member = member;
        this.time = localDateTime;
        this.type = type;
    }
}
