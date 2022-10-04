package project.bind.MenToMen.domain.post.dto;

import lombok.Getter;
import project.bind.MenToMen.domain.file.dto.ImgUrlResponseDto;
import project.bind.MenToMen.domain.post.domain.entity.Tag;

import java.util.List;

@Getter
public class PostUpdateDto {

    private Long postId;
    private List<ImgUrlResponseDto> imgUrls;
    private Tag tag;
    private String content;
}