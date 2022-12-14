package SW_Engineering.Group3.domain.Board;

import SW_Engineering.Group3.domain.auth.Member;
import SW_Engineering.Group3.domain.club.Club;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long boardID;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String content;

    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    private Club club;

    private String createTime;


    public Board(String author, Long boardID, String title, String content) {
        this.author = author;
        this.boardID = boardID;
        this.title = title;
        this.content = content;
        this.createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}