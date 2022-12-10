package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.auth.Authority;
import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.club.ClubAuthToken;
import SW_Engineering.Group3.domain.club.ClubMemberList;
import SW_Engineering.Group3.dto.MainResult;
import SW_Engineering.Group3.dto.Response;
import SW_Engineering.Group3.dto.club.ClubMainPageDto;
import SW_Engineering.Group3.dto.club.ClubRegisterDto;
import SW_Engineering.Group3.dto.club.JoinMemberDto;
import SW_Engineering.Group3.repository.club.ClubMemberRepository;
import SW_Engineering.Group3.repository.club.ClubRepository;
import SW_Engineering.Group3.repository.member.MemberRepository;
import SW_Engineering.Group3.repository.club.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final MemberRepository memberRepository;
    private final ClubMemberRepository clubMemberRepository;
    private final ClubRepository clubRepository;
    private final TokenRepository tokenRepository;
    private final Response response;

    /**
     * 동아리 정보를 저장
     */
    public Long registerClub(ClubRegisterDto clubRegisterDto){

        String clubName = clubRegisterDto.getClubName();
        String presidentName = clubRegisterDto.getPresidentName();

        ClubAuthToken savedToken = tokenRepository.findTokenByNameAndPresident(clubName, presidentName);

        if(savedToken != null && savedToken.getToken().equals(clubRegisterDto.getAuthenticationToken()))
            return clubRepository.save(clubRegisterDto.toClub()).getId();
        else
            return null;

    }

    /**
     * 동아리 메인화면에서 필요로 하는 동아리 정보를 반환하는 기능
     */
    public ClubMainPageDto getClubInfo(Long clubId){
        Optional<Club> optionalClub = clubRepository.findById(clubId);

        if(optionalClub.isPresent()){
            Club club = optionalClub.get();

            return ClubMainPageDto.builder()
                    .clubName(club.getClubName())
                    .presidentName(club.getPresidentName())
                    .category(club.getCategory())
                    .introduce(club.getIntroduce())
                    .memberCounts(club.getClubMembers().size())
                    .build();
        }
        else
            return null;
    }

    /**
     * 전달된 동아리 번호에 해당하는 동아리에, 유저 번호에 해당하는 유저를 등록시킴
     */
    public ResponseEntity registerUser(Long clubId, Long memberId) {

        // 1. 동아리, 유저 조회
        Optional<Club> findClub = clubRepository.findById(clubId);
        Optional<Member> findMember = memberRepository.findById(memberId);

        if(findClub.isEmpty())
            return response.fail("존재하지 않는 동아리입니다. 동아리 번호를 다시 확인해주세요.", HttpStatus.BAD_REQUEST);
        else if(findMember.isEmpty())
            return response.fail("존재하지 않는 유저입니다. 유저 번호를 다시 확인해주세요.", HttpStatus.BAD_REQUEST);

        Club joinClub = findClub.get();
        Member registerMember = findMember.get();

        // 2. 동아리와 유저에 각각 정보를 저장
        ClubMemberList clubMemberList = new ClubMemberList(registerMember, joinClub, Authority.ROLE_MEMBER);
        joinClub.getClubMembers().add(clubMemberList);
        registerMember.getJoinClubs().add(clubMemberList);

        // 3. 동아리-유저 정보에 생성한 정보를 저장
        clubMemberRepository.save(clubMemberList);

        return response.success("성공적으로 " + joinClub.getClubName() + "에 " + registerMember.getUserName() +"님이 등록됐습니다.");
    }

    /**
     * 전달된 동아리 번호에 해당하는 동아리에 속하는 모든 유저 정보를 반환
     */
    public MainResult viewClubMembers(Long clubId){

        //1. 전달받은 동아리 번호에 해당하는 동아리를 db로부터 가져옴
        Optional<Club> club = clubRepository.findById(clubId);
        if(club.isEmpty())
            return null;

        //2. 전달받은 동아리 번호에 해당하는 동아리가 가진 모든 동아리-유저 정보를 db로부터 가져옴
        List<ClubMemberList> joinList = clubMemberRepository.findByClub(club.get());

        //3. 동아리-유저 정보에서 유저 정보만 추출
        List<Member> memberList = joinList.stream()
                .map(listRow -> listRow.getMember())
                .collect(Collectors.toList());

        //4. 추출한 유저 정보를 dto로 전환
        List<JoinMemberDto> dtos = memberList.stream()
                .map(member -> JoinMemberDto.createJoinMemberDto(member))
                .collect(Collectors.toList());

        //5. dto에 id값 심은 후 반환
        for(int order = 0; order < dtos.size(); order++) {
            dtos.get(order).setOrder(Long.valueOf(order) + 1);
        }

        return new MainResult(dtos.size(), dtos);
    }

    /**
     * 유저가 동아리의 특정 페이지에 접근할 권한이 있는지 검증
     */
    public boolean checkUserClubAuthority(Long memberId, Long clubId, Authority requiredAuthority) {

        //1. Member_id로 저장된 Member 찾기
        Member member = memberRepository.findById(memberId).orElseGet(null);

        //2. 반환된 Member가 없으면 요청값 오류
        if(member == null) {
            return false;
        }

        //3. 유저-동아리 테이블에서 유저 정보를 기준으로 전달된 동아리 번호와 일치하는 동아리원 정보를 가져온다
        ClubMemberList clubMember = clubMemberRepository.findByMember(member).stream()
                .filter(c -> c.getClub().getId() == clubId)
                .findAny()
                .orElse(null);

        //4. 전달된 동아리 번호에 해당하는 동아리가 존재하지 않거나(유저의 동아리 미가입)
        //   유저의 접근 권한이 없으면 오류
        if(clubMember == null || clubMember.getAuthority().getRank() < requiredAuthority.getRank()) {
            return false;
        }

        return true;
    }

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Club findClubById(Long clubId) {
        return clubRepository.findById(clubId).orElse(null);
    }
}
