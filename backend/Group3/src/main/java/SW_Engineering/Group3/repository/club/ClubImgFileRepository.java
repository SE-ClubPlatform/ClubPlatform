package SW_Engineering.Group3.repository.club;

import SW_Engineering.Group3.domain.ClubImgFile;
import SW_Engineering.Group3.domain.club.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubImgFileRepository extends JpaRepository<ClubImgFile, Long> {

    ClubImgFile findClubImgFileByClub(Club club);
}
