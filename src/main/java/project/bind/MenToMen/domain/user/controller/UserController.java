package project.bind.MenToMen.domain.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.bind.MenToMen.domain.user.domain.User;
import project.bind.MenToMen.global.annotation.CheckToken;
import project.bind.MenToMen.global.response.DataResponse;

@Api(tags = "User-Controller")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @CheckToken
    @ApiOperation(value = "AccessToken 인증으로 내정보 받기")
    @GetMapping("/my")
    public ResponseEntity<DataResponse<User>> login(@RequestAttribute("user") User user) {
        return DataResponse.ok("유저 정보 조회 성공", user);
    }
}
