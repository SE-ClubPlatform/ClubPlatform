package SW_Engineering.Group3.dto.club;

import SW_Engineering.Group3.domain.club.Club;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubRegisterDto {

    @NotNull
    private String clubName;
    @NotNull
    private String presidentName;
    @NotNull
    private String category;
    @NotNull
    private String introduce;
    @NotNull
    private String authenticationToken;

    @Builder
    public ClubRegisterDto(String clubName, String presidentName, String category,
                           String introduce, String authenticationToken){
        this.clubName = clubName;
        this.presidentName = presidentName;
        this.category = category;
        this.introduce = introduce;
        this.authenticationToken = authenticationToken;
    }

    public Club toClub(){
        return Club.builder()
                .clubName(clubName)
                .presidentName(presidentName)
                .introduce(introduce)
                .category(category)
                .build();
    }

}
