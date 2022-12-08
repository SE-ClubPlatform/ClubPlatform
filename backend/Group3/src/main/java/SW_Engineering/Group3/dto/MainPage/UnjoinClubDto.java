package SW_Engineering.Group3.dto.MainPage;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UnjoinClubDto {

    private String clubName;
    private String category;

    public UnjoinClubDto(String clubName, String category){
        this.clubName = clubName;
        this.category = category;
    }
}
