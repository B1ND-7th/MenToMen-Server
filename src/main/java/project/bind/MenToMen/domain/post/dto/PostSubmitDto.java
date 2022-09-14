package project.bind.MenToMen.domain.post.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.post.domain.entity.Tag;
import project.bind.MenToMen.domain.user.domain.User;

import java.time.LocalDateTime;

@ApiModel
@Getter
public class PostSubmitDto {

    @ApiModelProperty(example = "받은 이미지 Url")
    private String imgUrl;
    @ApiModelProperty(example = "태그")
    private Tag tag;
    @ApiModelProperty(example = "내용")
    private String content;

    public Post toEntity(PostSubmitDto postSubmitDto, User user) {
        return Post.builder()
                .user(user)
                .postDateTime(LocalDateTime.now())
                .tag(postSubmitDto.getTag())
                .imgUrl(postSubmitDto.getImgUrl())
                .content(postSubmitDto.getContent())
                .build();
    }
}
