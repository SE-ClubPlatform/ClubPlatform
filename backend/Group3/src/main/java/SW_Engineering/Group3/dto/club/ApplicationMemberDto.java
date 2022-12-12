package SW_Engineering.Group3.dto.club;

import SW_Engineering.Group3.domain.auth.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationMemberDto {

    private String name; // 신청한 학생 이름
    private String studentId; // 신청한 학생 학번
    private String major; // 신청한 학생 전공
    private String phoneNumber; // 신청한 학생 핸드폰 번호

    @Builder
    private ApplicationMemberDto(String name, String studentId, String major, String phoneNumber) {
        this.name = name;
        this.studentId = studentId;
        this.major = major;
        this.phoneNumber = phoneNumber;
    }

    public static ApplicationMemberDto createApplicationMemberDto(Member member) {
        ApplicationMemberDto applicationMemberDto = new ApplicationMemberDto();

        applicationMemberDto.setName(member.getUserName());
        applicationMemberDto.setStudentId(member.getStudentId());
        applicationMemberDto.setMajor(member.getMajor());
        applicationMemberDto.setPhoneNumber(member.getPhoneNumber());

        return applicationMemberDto;
    }

}
