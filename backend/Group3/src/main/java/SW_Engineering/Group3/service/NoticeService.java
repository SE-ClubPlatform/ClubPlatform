package SW_Engineering.Group3.service;

import SW_Engineering.Group3.domain.Board.Notice;
import SW_Engineering.Group3.dto.Board.NoticeDto;
import SW_Engineering.Group3.dto.Board.NoticeUpdateDto;
import SW_Engineering.Group3.repository.Board.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    public Notice getNoticeInfo(Long boardID) {
        return noticeRepository.findById(boardID).orElse(null);
    }

    @Transactional
    public Long createNotice(NoticeDto noticeDto) {
        Notice notice = noticeDto.toNotice();
        return noticeRepository.save(notice).getBoardID();
    }

    public List<Notice> getRecentNotices() {
        List<Notice> allNotice = noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "create_date"));
        List<Notice> recentNotices = null;

        for (int i = 0; i < 5; i++) {
            recentNotices.add(allNotice.get(i));
            // 5개보다 공지사항이 적을 때
            if (i == allNotice.size()) break;
        }
        return recentNotices;
    }

    @Transactional
    public Long updateNotice(Long id, NoticeUpdateDto noticeUpdateDto) {
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        notice.update(noticeUpdateDto.getTitle(), noticeUpdateDto.getContent());

        return id;
    }

    @Transactional
    public void deleteNotice(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        noticeRepository.delete(notice);
    }

    public NoticeDto searchById(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        return new NoticeDto(notice.getBoardID(), notice.getTitle(), notice.getContent(), notice.getIsFinish());
    }
}
