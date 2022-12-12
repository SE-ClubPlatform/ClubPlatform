package SW_Engineering.Group3.dto.MainPage;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinClubDto {

    private Long clubId;
    private String clubName;
    private String image;

    public JoinClubDto(Long clubId, String clubName, String image){
        this.clubId = clubId;
        this.clubName = clubName;
        this.image = image;
    }

}
