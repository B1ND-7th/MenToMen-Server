package project.bind.MenToMen.domain.comment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.bind.MenToMen.domain.comment.domain.entities.Comment;
import project.bind.MenToMen.domain.user.domain.StdInfo;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private Long userId;
    private Long postId;
    private Long commentId;
    private String userName;
    private String profileUrl;
    private StdInfo stdInfo;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private String content;

    public CommentResponseDto(Comment comment) {
        this.userId = comment.getUser().getId();
        this.postId = comment.getPost().getId();
        this.commentId = comment.getId();
        this.userName = comment.getUser().getName();
        this.profileUrl = comment.getUser().getProfileImage();
        this.stdInfo = comment.getUser().getStdInfo();
        this.createDateTime = comment.getCreatedDate();
        this.updateDateTime = comment.getModifiedDate();
        this.content = comment.getContent();
    }

}
