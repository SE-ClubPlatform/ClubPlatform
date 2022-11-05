package SW_Engineering.Group3.domain.club;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ClubAuthToken {

    @Id @GeneratedValue
    private Long id;

    private String clubName;
    private String presidentName;
    private String token;

}
