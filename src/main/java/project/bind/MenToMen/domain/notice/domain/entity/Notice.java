package project.bind.MenToMen.domain.notice.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity(name = "notice")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Notice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "fk_user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User sendUser;

    @JoinColumn(name = "fk_post_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Builder
    public Notice(User sendUser, Post post) {
        this.sendUser = sendUser;
        this.post = post;
    }

}
