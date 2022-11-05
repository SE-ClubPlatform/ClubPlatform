package SW_Engineering.Group3.domain.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSession {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionId;
    private Long memberId;

    public UserSession(String sessionId, Long memberId){
        this.sessionId = sessionId;
        this.memberId = memberId;
    }
}
