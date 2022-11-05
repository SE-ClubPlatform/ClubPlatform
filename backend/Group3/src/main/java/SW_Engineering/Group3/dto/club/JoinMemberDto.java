package SW_Engineering.Group3.dto.club;

import SW_Engineering.Group3.domain.auth.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinMemberDto {

    private Long id;
    private String email;
    private String userName;
    private String studentId;
    private String major;
    private String phoneNumber;

    @Builder
    private JoinMemberDto(Long id, String email, String userName,
                         String studentId, String major, String phoneNumber){
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.studentId = studentId;
        this.major = major;
        this.phoneNumber = phoneNumber;
    }

    public static JoinMemberDto createJoinMemberDto(Member member){

        return JoinMemberDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .userName(member.getUserName())
                .studentId(member.getStudentId())
                .major(member.getMajor())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
