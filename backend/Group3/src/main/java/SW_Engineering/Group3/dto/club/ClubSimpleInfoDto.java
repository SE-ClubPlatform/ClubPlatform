package SW_Engineering.Group3.dto.club;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubSimpleInfoDto {

    private Long clubId; // 동아리 아이디
    private String clubName;
    private String presidentName;
    private String introduce;
    private String category;
    private int memberCounts;
    private String image;

    @Builder
    public ClubSimpleInfoDto(Long clubId, String clubName, String presidentName,
                             String introduce, String category, int memberCounts, String image) {

        this.clubId = clubId;
        this.clubName = clubName;
        this.presidentName = presidentName;
        this.introduce = introduce;
        this.category = category;
        this.memberCounts = memberCounts;
        this.image = image;

    }

}
