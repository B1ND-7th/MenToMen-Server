package project.bind.MenToMen.domain.post.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.post.domain.entity.Tag;
import project.bind.MenToMen.domain.post.domain.entity.UpdateStatus;
import project.bind.MenToMen.domain.user.domain.StdInfo;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostResponseDto {

    @ApiModelProperty(example = "기술 태그")
    private Tag tag;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private UpdateStatus updateStatus;
    @ApiModelProperty(example = "본문")
    private String content;
    private String imgUrl;
    private Long postId;
    private String userName;
    private String profileUrl;
    private StdInfo stdInfo;
    private Long author;

    public PostResponseDto(Post post) {
        this.tag = post.getTag();
        this.createDateTime = post.getCreatePostDateTime();
        this.updateDateTime = post.getUpdatePostDateTime();
        this.updateStatus = post.getUpdateStatus();
        this.content = post.getContent();
        this.imgUrl = post.getImgUrl();
        this.postId = post.getId();
        this.userName = post.getUser().getName();
        this.profileUrl = post.getUser().getProfileImage();
        this.stdInfo = post.getUser().getStdInfo();
        this.author = post.getUser().getId();
    }
}
