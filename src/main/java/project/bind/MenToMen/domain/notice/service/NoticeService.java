package project.bind.MenToMen.domain.notice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bind.MenToMen.domain.notice.domain.NoticeRepository;
import project.bind.MenToMen.domain.notice.domain.dto.NoticeStatus;
import project.bind.MenToMen.domain.notice.domain.entity.Notice;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.user.domain.User;

@Slf4j
@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeStatus noticeStatus(User user) {

    }

    @Transactional
    public void submitNotice(User user, Post post) {
        Notice notice = Notice.builder()
                .sendUser(user)
                .post(post).build();
        noticeRepository.save(notice);
    }
}
