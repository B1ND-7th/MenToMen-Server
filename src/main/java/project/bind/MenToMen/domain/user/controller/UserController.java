package project.bind.MenToMen.domain.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bind.MenToMen.domain.post.dto.PostsResponseDto;
import project.bind.MenToMen.domain.user.domain.User;
import project.bind.MenToMen.domain.user.dto.UserInfoResponseDto;
import project.bind.MenToMen.domain.user.service.UserService;
import project.bind.MenToMen.global.annotation.CheckToken;
import project.bind.MenToMen.global.response.DataResponse;

import java.util.List;

@Api(tags = "User-Controller")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @CheckToken
    @ApiOperation(value = "AccessToken 인증으로 내정보 받기")
    @GetMapping("/my")
    public ResponseEntity<DataResponse<UserInfoResponseDto>> login(@RequestAttribute("user") User user) {
        return DataResponse.ok("유저 정보 조회 성공", new UserInfoResponseDto(user));
    }

    @CheckToken
    @ApiOperation(value = "유저 게시물 조회")
    @GetMapping("/post")
    public ResponseEntity<DataResponse<List<PostsResponseDto>>> readUserPost(@RequestAttribute("user") User user) {
        return DataResponse.ok("유저 게시물 게시물 조회 성공", userService.findPostByUser(user));
    }
}
