package project.bind.MenToMen.domain.notice.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.bind.MenToMen.domain.notice.domain.entity.Notice;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeResponseDto {

    private Long postId;
    private String senderName;
    private String senderProfileImage;
    private String commentContent;
    private LocalDateTime createDateTime;
    private NoticeStatus noticeStatus;

    public NoticeResponseDto(Notice notice) {
        this.postId = notice.getPost().getId();
        this.senderName = notice.getSendUser().getName();
        this.senderProfileImage = notice.getSendUser().getProfileImage();
        this.commentContent = notice.getComment().getContent();
        this.createDateTime = notice.getCreatedDate();
        this.noticeStatus = notice.getNoticeStatus();
    }
}
