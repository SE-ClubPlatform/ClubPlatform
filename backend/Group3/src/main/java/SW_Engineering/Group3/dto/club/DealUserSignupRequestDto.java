package SW_Engineering.Group3.dto.club;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DealUserSignupRequestDto {

    private String studentId;
    private String status;

}
