package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.club.ClubMemberList;
import SW_Engineering.Group3.dto.member.JoinClubDto;
import SW_Engineering.Group3.repository.club.ClubMemberRepository;
import SW_Engineering.Group3.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final ClubMemberRepository clubMemberRepository;

    public Member findMemberById(Long memberId){
        Optional<Member> findMember = memberRepository.findById(memberId);

        return findMember.orElse(null);
    }

    public List<JoinClubDto> getJoinClubs(Long memberId) {

        Member member = findMemberById(memberId);

        if(member == null)
            return null;

        return clubMemberRepository.findByMember(member).stream()
                .map(ClubMemberList::getClub)
                .map(club -> new JoinClubDto(club.getClubName(), null))
                .collect(Collectors.toList());

    }

}
