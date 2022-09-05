package project.bind.MenToMen.domain.post.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bind.MenToMen.domain.post.domain.entity.Tags;
import project.bind.MenToMen.domain.post.dto.PostResponseDto;
import project.bind.MenToMen.domain.post.dto.PostsResponseDto;
import project.bind.MenToMen.domain.post.dto.PostSubmitDto;
import project.bind.MenToMen.domain.post.service.PostService;
import project.bind.MenToMen.domain.user.domain.User;
import project.bind.MenToMen.global.annotation.CheckToken;
import project.bind.MenToMen.global.response.DataResponse;
import project.bind.MenToMen.global.response.Response;

import java.util.List;

@Api(tags = "Post-Controller")
@RestController
@RequestMapping(value = "/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @CheckToken
    @ApiOperation(value = "게시물 등록")
    @PostMapping("/submit")
    public ResponseEntity<Response> post(@RequestAttribute("user") User user, @RequestBody PostSubmitDto postSubmitDto) {
        postService.post(postSubmitDto, user);
        return Response.ok("게시물 등록 성공");
    }

    @ApiOperation(value = "전체 게시물 조회")
    @GetMapping("/readAll")
    public ResponseEntity<DataResponse<List<PostsResponseDto>>> readAll() {
        return DataResponse.ok("전체 게시물 조회 성공", postService.findPostAll());
    }

    @ApiOperation(value = "단일 게시물 조회")
    @GetMapping("/readOne/{postId}")
    public ResponseEntity<DataResponse<PostResponseDto>> readOne(@PathVariable("postId") Long postId) {
        return DataResponse.ok("단일 게시물 조회 성공", postService.findPostOne(postId));
    }

    @ApiOperation(value = "태그로 게시물 조회")
    @GetMapping("/readAll/{tag}")
    public ResponseEntity<DataResponse<List<PostsResponseDto>>> readAllByTag(@PathVariable("tag")Tags tag) {
        return DataResponse.ok("태그로 게시물 조회 성공", postService.findPostByTag(tag));
    }
}
