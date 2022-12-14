package project.bind.MenToMen.domain.post.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.bind.MenToMen.domain.comment.domain.entity.Comment;
import project.bind.MenToMen.domain.post.domain.dto.PostUpdateDto;
import project.bind.MenToMen.domain.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> commentList = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tag tag;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createPostDateTime;

    @LastModifiedDate
    private LocalDateTime updatePostDateTime;

    @Column(columnDefinition = "LONGTEXT")
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
        if(imgUrl.isEmpty()) this.imgUrl = null;
        else this.imgUrl = imgUrl;
        this.updateStatus = UpdateStatus.NOT_UPDATE;
    }

    public void updateInfo(PostUpdateDto postUpdateDto) {
        this.tag = postUpdateDto.getTag();
        this.content = postUpdateDto.getContent();
        if(postUpdateDto.getImgUrls().isEmpty()) this.imgUrl = null;
        else this.imgUrl = String.join("///", postUpdateDto.getImgUrls().stream().map(
                imgUrlResponseDto -> imgUrlResponseDto.getImgUrl()).collect(Collectors.toList()));
        this.updateStatus = UpdateStatus.UPDATE;
    }
}
