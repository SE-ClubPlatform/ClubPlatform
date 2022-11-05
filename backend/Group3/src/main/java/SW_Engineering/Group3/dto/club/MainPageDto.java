package SW_Engineering.Group3.dto.club;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainPageDto {

    private String clubName;
    private String category;

    public MainPageDto(String clubName, String category){
        this.clubName = clubName;
        this.category = category;
    }
}
