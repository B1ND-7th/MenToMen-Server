package project.bind.MenToMen.domain.post.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.post.domain.entity.Tags;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostsResponseDto {

    @ApiModelProperty(example = "기술 태그")
    private Tags tags;
    private LocalDateTime localDateTime;
    private String content;
    private String imgUrl;
    private Long postId;
    private Long userId;
    private String userName;
    private String profileUrl;

    public PostsResponseDto(Post post) {
        this.tags = post.getTags();
        this.localDateTime = post.getPostDateTime();
        this.content = post.getContent();
        this.imgUrl = post.getImgUrl();
        this.postId = post.getId();
        this.userId = post.getUser().getId();
        this.userName = post.getUser().getName();
        this.profileUrl = post.getUser().getProfileImage();
    }
}
