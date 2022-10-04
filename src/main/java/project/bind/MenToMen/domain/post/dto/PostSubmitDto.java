package project.bind.MenToMen.domain.post.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import project.bind.MenToMen.domain.file.dto.ImgUrlResponseDto;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.post.domain.entity.Tag;
import project.bind.MenToMen.domain.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;


@ApiModel
@Getter
public class PostSubmitDto {

    @ApiModelProperty(example = "받은 이미지 Url")
    private List<ImgUrlResponseDto> imgUrls;
    @ApiModelProperty(example = "태그")
    private Tag tag;
    @ApiModelProperty(example = "내용")
    private String content;

    public Post toEntity(PostSubmitDto postSubmitDto, User user) {
        return Post.builder()
                .user(user)
                .tag(postSubmitDto.getTag())
                .content(postSubmitDto.getContent())
                .imgUrl(String.join("///", postSubmitDto.getImgUrls().stream().map(
                        imgUrlResponseDto -> imgUrlResponseDto.getImgUrl()).collect(Collectors.toList()))).build();
    }
}
