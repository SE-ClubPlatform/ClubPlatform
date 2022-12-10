package SW_Engineering.Group3.dto.club;

import SW_Engineering.Group3.domain.auth.Member;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinMemberDto {

    @Setter private Long order;
    private String userName;
    private String studentId;
    private String major;
    private String phoneNumber;

    @Builder
    private JoinMemberDto(String userName, String studentId, String major, String phoneNumber){
        this.userName = userName;
        this.studentId = studentId;
        this.major = major;
        this.phoneNumber = phoneNumber;
    }

    public static JoinMemberDto createJoinMemberDto(Member member) {

        return JoinMemberDto.builder()
                .userName(member.getUserName())
                .studentId(member.getStudentId())
                .major(member.getMajor())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
