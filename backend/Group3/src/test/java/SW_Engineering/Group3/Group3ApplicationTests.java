package SW_Engineering.Group3;

import SW_Engineering.Group3.domian.Member;
import SW_Engineering.Group3.dto.SignupDto;
import SW_Engineering.Group3.repository.MemberRepository;
import SW_Engineering.Group3.service.AuthService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class Group3ApplicationTests {

	@Autowired private AuthService authService;
	@Autowired private MemberRepository memberRepository;


	@Nested
	class AuthenticationTest {

		@Test
		void 정상적인_회원가입(){
			SignupDto signupDto = SignupDto.builder()
					.email("dnwls813@ajou.ac.kr")
					.password("a12345678!")
					.studentId("201820772")
					.major("소프트웨어학과")
					.phoneNumber("010-1234-5678")
					.build();

			Long saveMemberId = authService.signupUser(signupDto);
			Member findMember = memberRepository.findByEmail(signupDto.getEmail()).get();

			Assertions.assertThat(findMember.getId()).isEqualTo(saveMemberId);
		}

		@Test
		void 중복이메일_회원가입(){
			SignupDto signupDto = SignupDto.builder()
					.email("dnwls813@ajou.ac.kr")
					.password("a12345678!")
					.studentId("201820772")
					.major("소프트웨어학과")
					.phoneNumber("010-1234-5678")
					.build();

			Long memberId = authService.signupUser(signupDto);

			SignupDto signupDto2 = SignupDto.builder()
					.email("dnwls813@ajou.ac.kr")
					.password("a12345678!")
					.studentId("201820772")
					.major("소프트웨어학과")
					.phoneNumber("010-1234-5678")
					.build();

			Long member2Id = authService.signupUser(signupDto);

			Assertions.assertThat(memberId).isNotNull();
			Assertions.assertThat(member2Id).isNull();
		}

		@Test
		void loginTest(){

		}
	}
}
