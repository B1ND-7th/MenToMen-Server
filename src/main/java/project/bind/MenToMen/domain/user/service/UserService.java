package project.bind.MenToMen.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bind.MenToMen.domain.post.domain.entity.Post;
import project.bind.MenToMen.domain.post.dto.PostResponseDto;
import project.bind.MenToMen.domain.user.domain.User;
import project.bind.MenToMen.domain.user.domain.UserRepository;
import project.bind.MenToMen.global.error.CustomError;
import project.bind.MenToMen.global.error.ErrorCode;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User save(User user) {
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        return byEmail.orElseGet(() -> userRepository.save(user));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> {
            throw CustomError.of(ErrorCode.NOT_FOUND);
        });
    }

    public List<PostResponseDto> findPostByUser(User user) {
        return userRepository.findById(user.getId()).get().getPosts()
                .stream().sorted(Comparator.comparing(Post::getId).reversed())
                .map(post -> new PostResponseDto(post)).collect(Collectors.toList());
    }
}
