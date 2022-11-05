package SW_Engineering.Group3.dto.club;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubMainPageDto {

    private String clubName;
    private String presidentName;
    private String introduce;
    private String category;
    private int memberCounts;

    @Builder
    public ClubMainPageDto(String clubName, String presidentName, String introduce,
                           String category, int memberCounts){

        this.clubName = clubName;
        this.presidentName = presidentName;
        this.introduce = introduce;
        this.category = category;
        this.memberCounts = memberCounts;
    }

}
