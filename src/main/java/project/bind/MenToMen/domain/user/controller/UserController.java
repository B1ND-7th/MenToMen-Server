package project.bind.MenToMen.domain.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.bind.MenToMen.domain.user.domain.User;
import project.bind.MenToMen.domain.user.dto.AccessTokenDto;
import project.bind.MenToMen.global.annotation.CheckLogin;
import project.bind.MenToMen.global.response.DataResponse;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @CheckLogin
    @GetMapping("/login")
    public void login(@RequestAttribute("user") User user) {
        log.info(user.getName());
    }

    @CheckLogin
    @GetMapping("/accessToken")
    public ResponseEntity<DataResponse<AccessTokenDto>> getAccessToken(@RequestAttribute("accessToken") AccessTokenDto accessTokenDto) {
        return DataResponse.ok("토큰 생성 성공", accessTokenDto);
    }
}
