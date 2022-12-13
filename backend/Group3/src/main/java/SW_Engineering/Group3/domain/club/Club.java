package SW_Engineering.Group3.domain.club;

import SW_Engineering.Group3.domain.ClubImgFile;
import SW_Engineering.Group3.domain.clubroom.ClubRoom;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Club {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String clubName; // 클럽 이름
    @Column
    private String presidentName; // 회장 이름
    @Column
    private String introduce; // 한 줄 소개
    @Column
    private String category; // 클럽 분류

    @OneToOne(mappedBy = "club", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Setter
    private ClubImgFile clubImgFile; // 동아리 이미지

    @JsonManagedReference
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<ClubMemberList> clubMembers; // 동아리 부원들

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private ClubRoom clubRoom; // 동아리방

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<ClubApplication> applications; // 동아리 가입 신청 목록

    @Builder
    public Club(String clubName, String presidentName, String introduce, String category){
        this.clubName = clubName;
        this.presidentName = presidentName;
        this.introduce = introduce;
        this.category = category;
    }

}
