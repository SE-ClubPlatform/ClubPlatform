package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.dto.auth.GoogleLoginResponse;
import SW_Engineering.Group3.dto.auth.LoginDto;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.repository.member.MemberRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GoogleService {

    private final MemberRepository memberRepository;
    private final AuthService authService;
    private final Response response;

    @Value("${GOOGLE_CLIENT_ID}")
    private String GOOGLE_CLIENT_ID;

    @Value("${GOOGLE_CLIENT_SECRET}")
    private String GOOGLE_CLIENT_SECRET;

    @Value("${GOOGLE_REDIRECT_URI}")
    private String GOOGLE_REDIRECT_URI;

    public String getGoogleUrl(){
        return "https://accounts.google.com/o/oauth2/auth?" +
                "client_id=" + GOOGLE_CLIENT_ID + "&" +
                "redirect_uri=" + GOOGLE_REDIRECT_URI + "&" +
                "response_type=code" +
                "&scope=email%20profile%20openid" +
                "&access_type=offline";
    }

    public String getIdToken(String code) throws JsonProcessingException {
        String GOOGLE_TOKEN_REQUEST_URL="https://oauth2.googleapis.com/token";

        RestTemplate restTemplate=new RestTemplate();
        //restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        Map<String, Object> params = new HashMap<>();

        params.put("code", code);
        params.put("client_id", GOOGLE_CLIENT_ID);
        params.put("client_secret", GOOGLE_CLIENT_SECRET);
        params.put("redirect_uri", GOOGLE_REDIRECT_URI);
        params.put("grant_type", "authorization_code");

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(GOOGLE_TOKEN_REQUEST_URL, params, String.class);

        if(responseEntity.getStatusCode()== HttpStatus.OK) {
            // ObjectMapper를 통해 String to Object로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // NULL이 아닌 값만 응답받기(NULL인 경우는 생략)

            GoogleLoginResponse googleLoginResponse = objectMapper.readValue(responseEntity.getBody(), new TypeReference<GoogleLoginResponse>() {});
            return googleLoginResponse.getIdToken();
        }

        return null;
    }

    public ResponseEntity getUserProfile(String idToken) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        String requestUrl = UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/tokeninfo")
                .queryParam("id_token", idToken).encode().toUriString();

        LoginDto responseEntity = restTemplate.getForObject(requestUrl, LoginDto.class);
        String email = responseEntity.getEmail();

        if(!memberRepository.existsByEmail(email)) { // 회원가입
            Member tempMember = Member.builder()
                    .email(email)
                    .build();

            Long memberId = memberRepository.save(tempMember).getId();

            LoginDto loginDto = LoginDto.builder()
                    .email(email)
                    .build();

            return response.fail(loginDto, "추가적인 유저정보가 필요합니다.", HttpStatus.BAD_REQUEST);
        }

        LoginDto loginDto = LoginDto.builder()
                .email(email)
                .password("password")
                .build();

        return authService.login(loginDto, true);
    }

}
