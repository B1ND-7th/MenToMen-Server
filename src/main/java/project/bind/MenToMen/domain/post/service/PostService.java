package project.bind.MenToMen.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bind.MenToMen.domain.post.domain.PostRepository;
import project.bind.MenToMen.domain.post.domain.entity.Tag;
import project.bind.MenToMen.domain.post.dto.PostResponseDto;
import project.bind.MenToMen.domain.post.dto.PostUpdateDto;
import project.bind.MenToMen.domain.post.dto.PostsResponseDto;
import project.bind.MenToMen.domain.post.dto.PostSubmitDto;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.user.domain.User;
import project.bind.MenToMen.global.error.CustomError;
import project.bind.MenToMen.global.error.ErrorCode;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void post(PostSubmitDto postSubmitDto, User user) {
        postRepository.save(postSubmitDto.toEntity(postSubmitDto, user));
    }

    public List<PostsResponseDto> findPostAll() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "Id")).stream()
                .map(post -> new PostsResponseDto(post))
                .collect(Collectors.toList());
    }

    public PostResponseDto findPostOne(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> {
            throw CustomError.of(ErrorCode.NOT_FOUND);
        });
        return new PostResponseDto(post);
    }

    public List<PostsResponseDto> findPostByTag(Tag tag) {
        return postRepository.findAllByTag(tag, Sort.by(Sort.Direction.DESC, "Id")).stream()
                .map( post -> new PostsResponseDto(post))
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(PostUpdateDto postUpdateDto, User user) {
        postRepository.findById(postUpdateDto.getPostId()).ifPresentOrElse(
                post -> {
                    User postUser = post.getUser();
                    if(user.getId().equals(postUser.getId())) {
                        post.updateInfo(postUpdateDto);
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
                    } else throw CustomError.of(ErrorCode.WRONG_USER);
                },
                () -> { throw CustomError.of(ErrorCode.NOT_FOUND);});
    }
}
