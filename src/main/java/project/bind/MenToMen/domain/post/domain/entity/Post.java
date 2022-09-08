package project.bind.MenToMen.domain.post.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.bind.MenToMen.domain.post.dto.PostUpdateDto;
import project.bind.MenToMen.domain.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tags tags;

    @Column(nullable = false)
    private LocalDateTime postDateTime;

    private String imgUrl;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String content;

    public void updateInfo(PostUpdateDto postUpdateDto) {
        this.tags = postUpdateDto.getTags();
        this.content = postUpdateDto.getContent();
        this.imgUrl = postUpdateDto.getImgUrl();
    }
}
