package project.bind.MenToMen.domain.post.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bind.MenToMen.domain.post.domain.entity.Tag;
import project.bind.MenToMen.domain.post.domain.dto.PostResponseDto;
import project.bind.MenToMen.domain.post.domain.dto.PostUpdateDto;
import project.bind.MenToMen.domain.post.domain.dto.PostSubmitDto;
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
    public ResponseEntity<Response> submit(@RequestAttribute User user, @RequestBody PostSubmitDto postSubmitDto) {
        postService.submit(postSubmitDto, user);
        return Response.ok("게시물 등록 성공");
    }

    @ApiOperation(value = "전체 게시물 조회")
    @GetMapping("/read-all")
    public ResponseEntity<DataResponse<List<PostResponseDto>>> readAll() {
        return DataResponse.ok("전체 게시물 조회 성공", postService.findPostAll());
    }

    @ApiOperation(value = "단일 게시물 조회")
    @GetMapping("/read-one/{postId}")
    public ResponseEntity<DataResponse<PostResponseDto>> readOne(@PathVariable("postId") Long postId) {
        return DataResponse.ok("단일 게시물 조회 성공", postService.findPostOne(postId));
    }

    @ApiOperation(value = "태그로 게시물 조회")
    @GetMapping("/read-all/{tag}")
    public ResponseEntity<DataResponse<List<PostResponseDto>>> readAllByTag(@PathVariable("tag") Tag tag) {
        return DataResponse.ok("태그로 게시물 조회 성공", postService.findPostByTag(tag));
    }

    @CheckToken
    @ApiOperation(value = "게시물 수정")
    @PatchMapping("/update")
    public ResponseEntity<Response> update(@RequestAttribute User user, @RequestBody PostUpdateDto postUpdateDto) {
        postService.update(postUpdateDto, user);
        return Response.ok("게시물 수정 성공");
    }

    @CheckToken
    @ApiOperation(value = "게시물 삭제")
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Response> delete(@RequestAttribute User user, @PathVariable("postId")Long postId) {
        postService.delete(postId, user);
        return Response.ok("게시물 삭제 성공");
    }

}
