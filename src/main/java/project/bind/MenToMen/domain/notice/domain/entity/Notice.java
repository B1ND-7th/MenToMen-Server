package project.bind.MenToMen.domain.notice.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.bind.MenToMen.domain.comment.domain.entity.Comment;
import project.bind.MenToMen.domain.notice.domain.dto.NoticeStatus;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity(name = "notice")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @JoinColumn(name = "fk_sender_user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User sendUser;

    @JoinColumn(name = "fk_writer_user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User writerUser;

    @JoinColumn(name = "fk_post_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @JoinColumn(name = "fk_comment_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NoticeStatus noticeStatus;

    @Builder
    public Notice(User sendUser, User writerUser, Post post, Comment comment) {
        this.sendUser = sendUser;
        this.writerUser = writerUser;
        this.post = post;
        this.comment = comment;
        this.noticeStatus = NoticeStatus.EXIST;
    }

    public void updateNoticeStatus() {
        this.noticeStatus = NoticeStatus.NONE;
    }
}
