package SW_Engineering.Group3.domain.workflow;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class VoteContent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    @Column
    private String content; // 투표 선택지 내용

    @Column
    private int count; // 해당 선택지가 투표받은 횟수

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    private Vote vote;

    public VoteContent(String content) {
        this.content = content;
        this.count = 0;
    }

    /**
     사용자들이 선택지를 투표할 시 count가 올라가도록 함
     */
    public void addCount() {
        this.count ++;
    }
}
