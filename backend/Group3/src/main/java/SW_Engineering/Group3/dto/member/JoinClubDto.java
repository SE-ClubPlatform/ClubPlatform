package SW_Engineering.Group3.dto.member;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinClubDto {

    private String clubName;
    private String image; // rds 만든 후 추가할 예정입니다

    public JoinClubDto(String clubName, String image){
        this.clubName = clubName;
    }

}
