package SW_Engineering.Group3;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.auth.LoginDto;
import SW_Engineering.Group3.dto.auth.SignupDto;
import SW_Engineering.Group3.repository.MemberRepository;
import SW_Engineering.Group3.service.AuthService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class Group3ApplicationTests {

	@Autowired private AuthService authService;
	@Autowired private MemberRepository memberRepository;
	@Autowired private Response response;


	@Nested
	class AuthenticationTest {

		//given
		@Test
		void 정상적인_회원가입(){
			SignupDto signupDto = SignupDto.builder()
					.email("dnwls813@ajou.ac.kr")
					.password("a12345678!")
					.studentId("201820772")
					.major("소프트웨어학과")
					.phoneNumber("010-1234-5678")
					.build();

			//when
			Long saveMemberId = authService.signupUser(signupDto);
			Member findMember = memberRepository.findByEmail(signupDto.getEmail()).get();

			//then
			Assertions.assertThat(findMember.getId()).isEqualTo(saveMemberId);
		}

		@Test
		void 중복이메일_회원가입(){

			//given
			SignupDto signupDto = SignupDto.builder()
					.email("dnwls813@ajou.ac.kr")
					.password("a12345678!")
					.studentId("201820772")
					.major("소프트웨어학과")
					.phoneNumber("010-1234-5678")
					.build();

			SignupDto signupDto2 = SignupDto.builder()
					.email("dnwls813@ajou.ac.kr")
					.password("a12345678!")
					.studentId("201820772")
					.major("소프트웨어학과")
					.phoneNumber("010-1234-5678")
					.build();

			//when
			Long memberId = authService.signupUser(signupDto);
			Long member2Id = authService.signupUser(signupDto);

			//then
			Assertions.assertThat(memberId).isNotNull();
			Assertions.assertThat(member2Id).isNull();
		}

		@Test
		void 로그인_성공(){

			//given
			SignupDto signupDto = SignupDto.builder()
					.email("dnwls813@ajou.ac.kr")
					.password("a12345678!")
					.studentId("201820772")
					.major("소프트웨어학과")
					.phoneNumber("010-1234-5678")
					.build();

			authService.signupUser(signupDto);

			//when
			LoginDto loginDto = LoginDto.builder()
					.email("dnwls813@ajou.ac.kr")
					.password("a12345678!")
					.build();

			ResponseEntity responseEntity = authService.login(loginDto, false);

			//then
			Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

		}

		@Test
		void 틀린_아이디_로그인(){

			//given
			SignupDto signupDto = SignupDto.builder()
					.email("dnwls813@ajou.ac.kr")
					.password("a12345678!")
					.studentId("201820772")
					.major("소프트웨어학과")
					.phoneNumber("010-1234-5678")
					.build();

			authService.signupUser(signupDto);

			//when
			LoginDto loginDto = LoginDto.builder()
					.email("dnwls123@ajou.ac.kr")
					.password("a12345678!")
					.build();

			ResponseEntity responseEntity = authService.login(loginDto, false);

			//then
			Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

		}

		@Test
		void 틀린_비밀번호_로그인(){

			//given
			SignupDto signupDto = SignupDto.builder()
					.email("dnwls813@ajou.ac.kr")
					.password("a12345678!")
					.studentId("201820772")
					.major("소프트웨어학과")
					.phoneNumber("010-1234-5678")
					.build();

			authService.signupUser(signupDto);

			//when
			LoginDto loginDto = LoginDto.builder()
					.email("dnwls813@ajou.ac.kr")
					.password("a!")
					.build();

			ResponseEntity responseEntity = authService.login(loginDto, false);
			System.out.println(responseEntity.getBody().toString());

			//then
			Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		}
	}
}