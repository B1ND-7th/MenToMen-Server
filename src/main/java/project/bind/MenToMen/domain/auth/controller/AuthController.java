package project.bind.MenToMen.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bind.MenToMen.domain.auth.dto.*;
import project.bind.MenToMen.domain.auth.service.AuthService;
import project.bind.MenToMen.global.response.DataResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    private String clientId = "39bc523458c14eb987b7b16175426a31a9f105b7f5814f1f9eca7d454bd23c73";
    private String clientSecret = "e90b070b437f420eb788fad746e97a507984328ddf9142f481397ca6e7afda0e";
    private String redirectUrl = "http://localhost:3000/callback";

    @GetMapping("/url")
    public DAuthClientResponseDto reqUrl(){
        return authService.getLoginUrl(clientId, redirectUrl);
    }

    @PostMapping("/code")
    public ResponseEntity<DataResponse<TokenResponseDto>> resCode(@RequestBody DAuthClientRequestDto dAuthClientRequestDto){
        TokenResponseDto token = authService.getToken(new DAuthApiRequestDto(dAuthClientRequestDto.getCode(), clientId, clientSecret));
        System.out.println("accessToken = " + token.getAccessToken());
        System.out.println("refreshToken = " + token.getRefreshToken());
        return DataResponse.ok("인증 성공", token);
    }
}
