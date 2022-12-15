package SW_Engineering.Group3.domain.clubroom;

import SW_Engineering.Group3.domain.club.Club;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubRoom {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id; // pk

    @Column
    private int currentMembers = 0; // 동방 현재 인원

    @Column
    private String location; // 동방 위치

    @OneToOne(mappedBy = "clubRoom", fetch = FetchType.LAZY)
    private Club club; // 소속 동아리

    @OneToMany(mappedBy = "clubRoom")
    private List<ClubRoomLog> clubRoomLog; // 출입 기록 모음

    public void minusCount() {
        this.currentMembers -= 1;
    }

    public void addCount() {
        this.currentMembers += 1;
    }

}
