package project.bind.MenToMen.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bind.MenToMen.domain.file.service.FileService;
import project.bind.MenToMen.domain.post.domain.PostRepository;
import project.bind.MenToMen.domain.post.domain.dto.PostSearchDto;
import project.bind.MenToMen.domain.post.domain.entity.Tag;
import project.bind.MenToMen.domain.post.domain.dto.PostResponseDto;
import project.bind.MenToMen.domain.post.domain.dto.PostUpdateDto;
import project.bind.MenToMen.domain.post.domain.dto.PostSubmitDto;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.user.domain.User;
import project.bind.MenToMen.global.error.CustomError;
import project.bind.MenToMen.global.error.ErrorCode;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final FileService fileService;

    @Transactional
    public void submit(PostSubmitDto postSubmitDto, User user) {
        postRepository.save(postSubmitDto.toEntity(postSubmitDto, user));
    }

    public List<PostResponseDto> findPostAll() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .map(post -> new PostResponseDto(post))
                .collect(Collectors.toList());
    }

    public PostResponseDto findPostOne(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> {
            throw CustomError.of(ErrorCode.NOT_FOUND);
        });
        return new PostResponseDto(post);
    }

    public List<PostResponseDto> findPostByTag(Tag tag) {
        return postRepository.findAllByTag(tag, Sort.by(Sort.Direction.DESC, "id")).stream()
                .map( post -> new PostResponseDto(post))
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(PostUpdateDto postUpdateDto, User user) {
        postRepository.findById(postUpdateDto.getPostId()).ifPresentOrElse(
                post -> {
                    User postUser = post.getUser();
                    if(user.getId().equals(postUser.getId())) {
                        post.updateInfo(postUpdateDto);
                        Optional.ofNullable(post.getImgUrl())
                                .ifPresent(url -> { if(!url.equals(postUpdateDto.getImgUrl()))
                                fileService.delete(url);
                        });
                    } else throw CustomError.of(ErrorCode.WRONG_USER);
                },
                () -> { throw CustomError.of(ErrorCode.NOT_FOUND);});
    }

    @Transactional
    public void delete(Long postId, User user) {
        postRepository.findById(postId).ifPresentOrElse(
                post -> {
                    User postUser = post.getUser();
                    if(user.getId().equals(postUser.getId())) {
                        postRepository.delete(post);
                        Optional.ofNullable(post.getImgUrl())
                                .ifPresent(url -> fileService.delete(url));
                    } else throw CustomError.of(ErrorCode.WRONG_USER);
                },
                () -> { throw CustomError.of(ErrorCode.NOT_FOUND);});
    }

    public List<PostResponseDto> search(PostSearchDto postSearchDto) {
        return postRepository.findByContentContaining(postSearchDto.getKeyword()).stream()
                .map(post -> new PostResponseDto(post)).collect(Collectors.toList());
    }

}
