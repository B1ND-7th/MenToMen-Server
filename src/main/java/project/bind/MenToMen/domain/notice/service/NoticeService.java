package project.bind.MenToMen.domain.notice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bind.MenToMen.domain.comment.domain.entity.Comment;
import project.bind.MenToMen.domain.notice.domain.NoticeRepository;
import project.bind.MenToMen.domain.notice.domain.dto.NoticeResponseDto;
import project.bind.MenToMen.domain.notice.domain.entity.NoticeStatus;
import project.bind.MenToMen.domain.notice.domain.dto.NoticeStatusDto;
import project.bind.MenToMen.domain.notice.domain.entity.Notice;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public List<NoticeResponseDto> findAllNotice(User user) {
        List<Notice> notices = noticeRepository.findAllByWriterUser(user, Sort.by(Sort.Direction.DESC, "id"));
        List<NoticeResponseDto> noticeResponseDtos = notices.stream()
                .map(notice -> new NoticeResponseDto(notice))
                .collect(Collectors.toList());
        updateNoticeStatus(notices);
        return noticeResponseDtos;
    }

    @Transactional
    protected void updateNoticeStatus(List<Notice> notices) {
        for (Notice notice : notices) {
            notice.updateNoticeStatus();
        }
    }

    @Transactional
    public void submitNotice(User user, Post post, Comment comment) {
        Notice notice = Notice.builder()
                .sendUser(user)
                .post(post)
                .comment(comment)
                .writerUser(post.getUser()).build();
        noticeRepository.save(notice);
    }

    public NoticeStatusDto getNoticeStatus(User user) {
        List<Notice> noticeList = noticeRepository.findAllByWriterUser(user, Sort.by(Sort.Direction.DESC, "id"));
        for (Notice notice : noticeList) {
            if(notice.getNoticeStatus().equals(NoticeStatus.EXIST)) return new NoticeStatusDto(NoticeStatus.EXIST);
        } return new NoticeStatusDto(NoticeStatus.NONE);
    }
}
