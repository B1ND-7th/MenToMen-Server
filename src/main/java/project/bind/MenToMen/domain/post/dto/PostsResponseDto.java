package project.bind.MenToMen.domain.post.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.post.domain.entity.Tags;
import project.bind.MenToMen.domain.user.domain.StdInfo;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostsResponseDto {

    @ApiModelProperty(example = "기술 태그")
    private Tags tags;
    private LocalDateTime localDateTime;
    @ApiModelProperty(example = "본문")
    private String content;
    private String imgUrl;
    private Long postId;
    private String userName;
    private String profileUrl;
    private StdInfo stdInfo;

    public PostsResponseDto(Post post) {
        this.tags = post.getTags();
        this.localDateTime = post.getPostDateTime();
        this.content = post.getContent();
        this.imgUrl = post.getImgUrl();
        this.postId = post.getId();
        this.userName = post.getUser().getName();
        this.profileUrl = post.getUser().getProfileImage();
        this.stdInfo = post.getUser().getStdInfo();
    }
}
