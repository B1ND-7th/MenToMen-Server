package project.bind.MenToMen.domain.comment.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.bind.MenToMen.domain.comment.domain.dto.CommentUpdateDto;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_post_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UpdateStatus updateStatus;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String content;

    @Builder
    public Comment(User user, Post post, String content) {
        this.user = user;
        this.post = post;
        this.updateStatus = UpdateStatus.NOT_UPDATE;
        this.content = content;
    }

    public void update(CommentUpdateDto commentUpdateDto) {
        this.content = commentUpdateDto.getContent();
        this.updateStatus = UpdateStatus.UPDATE;
    }

}
