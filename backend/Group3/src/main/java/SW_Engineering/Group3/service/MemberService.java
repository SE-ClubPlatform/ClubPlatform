package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.club.ClubMemberList;
import SW_Engineering.Group3.repository.club.ClubMemberRepository;
import SW_Engineering.Group3.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final ClubMemberRepository clubMemberRepository;
    private final ClubService clubService;

    public Member findMemberById(Long memberId){
        Optional<Member> findMember = memberRepository.findById(memberId);

        return findMember.orElse(null);
    }

    /**
     * 유저가 속한 모든 동아리 조회
     */
    public List<ClubMemberList> getJoinClubs(Long memberId) {

        // 1. 유저 조회
        Member member = findMemberById(memberId);

        if(member == null)
            return null;

        //2. 유저를 기준으로 찾은 유저-동아리 정보 반환
        return clubMemberRepository.findByMember(member);

    }

    /**
     * 유저가 속하지 않은 동아리 중 랜덤한 3개 반환
     */
    public List<Club> getRandomClubs(Long memberId) {

        // 1. 유저 조회
        Member member = findMemberById(memberId);

        if(member == null) {
            return null;
        }

        //2. 유저가 가입한 모든 동아리 조회
        List<Club> joinedClubs = getJoinClubs(memberId).stream()
                .map(clubMemberList -> clubMemberList.getClub())
                .collect(Collectors.toList());

        //3. 유저가 가입하지 않은 모든 동아리 구함
        List<Club> notJoinedClubs = clubService.getAllClubs().stream()
                .filter(c -> !joinedClubs.contains(c))
                .collect(Collectors.toList());

        //4. (3) 과정에서 얻은 동아리 수가 3개를 넘을 경우, 그 중 랜덤한 동아리 3개 반환
        if(notJoinedClubs.size() > 3){
            while(notJoinedClubs.size() > 3) {
                int randomId = (int)(Math.random() * notJoinedClubs.size());
                notJoinedClubs.remove(randomId);
            }
        }

        Collections.shuffle(notJoinedClubs);

        return notJoinedClubs;
    }

}
