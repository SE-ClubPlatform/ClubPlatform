package SW_Engineering.Group3.domain;

import SW_Engineering.Group3.domain.club.Club;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ClubImgFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @Column String filename;
    @Column String originalFileName;
    @Column String fileUrl;

    @Builder
    public ClubImgFile(Club club, String filename, String originalFileName, String fileUrl){
        this.club = club;
        this.filename = filename;
        this.originalFileName = originalFileName;
        this.fileUrl = fileUrl;
    }
}
