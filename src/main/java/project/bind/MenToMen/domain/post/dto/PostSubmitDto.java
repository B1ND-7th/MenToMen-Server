package project.bind.MenToMen.domain.post.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.post.domain.entity.Tags;
import project.bind.MenToMen.domain.user.domain.User;

import java.time.LocalDateTime;

@ApiModel
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostSubmitDto {

    @ApiModelProperty(example = "받은 이미지 Url")
    private String imgUrl;
    @ApiModelProperty(example = "태그")
    private Tags tags;
    @ApiModelProperty(example = "내용")
    private String content;

    public Post toEntity(PostSubmitDto postSubmitDto, User user) {
        return Post.builder()
                .user(user)
                .postDateTime(LocalDateTime.now())
                .tags(postSubmitDto.getTags())
                .imgUrl(postSubmitDto.getImgUrl())
                .content(postSubmitDto.getContent())
                .build();
    }
}
