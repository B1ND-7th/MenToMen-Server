package project.bind.MenToMen.domain.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bind.MenToMen.domain.post.domain.PostRepository;
import project.bind.MenToMen.domain.post.dto.PostResponseDto;
import project.bind.MenToMen.domain.post.dto.PostsResponseDto;
import project.bind.MenToMen.domain.post.dto.PostSubmitDto;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.user.domain.User;
import project.bind.MenToMen.domain.user.domain.UserRepository;
import project.bind.MenToMen.global.error.CustomError;
import project.bind.MenToMen.global.error.ErrorCode;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    @Value("${product.IPv4}")
    private String IP;

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Post post(PostSubmitDto postSubmitDto) {
        User user = checkUser(postSubmitDto.getUserId());
        return postRepository.save(postSubmitDto.toEntity(postSubmitDto, user));
    }

    private User checkUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            throw CustomError.of(ErrorCode.NOT_FOUND);
        });
    }

    public List<PostsResponseDto> findPostAll() {
        return postRepository.findAll().stream()
                .map(post -> new PostsResponseDto(post))
                .collect(Collectors.toList());
    }

    public PostResponseDto findPostOne(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> {
            throw CustomError.of(ErrorCode.NOT_FOUND);
        });
        return new PostResponseDto(post);
    }
}
