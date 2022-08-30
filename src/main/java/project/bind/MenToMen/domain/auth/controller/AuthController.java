package project.bind.MenToMen.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bind.MenToMen.domain.auth.dto.api.DAuthApiRequestDto;
import project.bind.MenToMen.domain.auth.dto.req.DAuthClientRequestDto;
import project.bind.MenToMen.domain.auth.dto.res.DAuthClientResponseDto;
import project.bind.MenToMen.domain.auth.dto.res.TokenResponseDto;
import project.bind.MenToMen.domain.auth.service.AuthService;
import project.bind.MenToMen.domain.auth.dto.res.AccessTokenDto;
import project.bind.MenToMen.global.annotation.CheckToken;
import project.bind.MenToMen.global.response.DataResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Value("${product.client-id}")
    private String clientId;
    @Value("${product.client-secret}")
    private String clientSecret;
    @Value("${product.redirect-url}")
    private String redirectUrl;

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

    @CheckToken
    @GetMapping("/refreshToken")
    public ResponseEntity<DataResponse<AccessTokenDto>> getAccessToken(@RequestAttribute("accessToken") AccessTokenDto accessTokenDto) {
        return DataResponse.ok("토큰 생성 성공", accessTokenDto);
    }
}
