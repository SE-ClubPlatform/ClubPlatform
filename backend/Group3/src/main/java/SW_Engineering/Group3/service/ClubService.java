package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.club.Club;
import SW_Engineering.Group3.domain.club.ClubAuthToken;
import SW_Engineering.Group3.dto.club.ClubMainPageDto;
import SW_Engineering.Group3.dto.club.ClubRegisterDto;
import SW_Engineering.Group3.repository.ClubRepository;
import SW_Engineering.Group3.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final TokenRepository tokenRepository;

    /**
     * 동아리 정보를 저장
     */
    public Long registerClubInfo(ClubRegisterDto clubRegisterDto){

        String clubName = clubRegisterDto.getClubName();
        String presidentName = clubRegisterDto.getPresidentName();

        ClubAuthToken savedToken = tokenRepository.findTokenByNameAndPresident(clubName, presidentName);

        if(savedToken.getToken().equals(clubRegisterDto.getAuthenticationToken()))
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
                    .introduce(club.getIntroduce())
                    .memberCounts(club.getClubMembers().size())
                    .build();
        }
        else
            return null;
    }
}
