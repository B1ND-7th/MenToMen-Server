package project.bind.MenToMen.domain.post.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.bind.MenToMen.domain.post.domain.dto.PostUpdateDto;
import project.bind.MenToMen.domain.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fk_post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tag tag;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createPostDateTime;

    @LastModifiedDate
    private LocalDateTime updatePostDateTime;

    private String imgUrl;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UpdateStatus updateStatus;

    @Builder
    public Post(User user, Tag tag, String content, String imgUrl) {
        this.user = user;
        this.tag = tag;
        this.content = content;
        this.imgUrl = imgUrl;
        this.updateStatus = UpdateStatus.NOT_UPDATE;
    }

    public void updateInfo(PostUpdateDto postUpdateDto) {
        this.tag = postUpdateDto.getTag();
        this.content = postUpdateDto.getContent();
        this.imgUrl = postUpdateDto.getImgUrl();
        this.updateStatus = UpdateStatus.UPDATE;
    }
}
