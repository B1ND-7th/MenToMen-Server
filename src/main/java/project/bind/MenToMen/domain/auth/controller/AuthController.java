package project.bind.MenToMen.domain.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bind.MenToMen.domain.auth.dto.api.DAuthApiRequestDto;
import project.bind.MenToMen.domain.auth.dto.req.DAuthClientRequestDto;
import project.bind.MenToMen.domain.auth.dto.res.TokenResponseDto;
import project.bind.MenToMen.domain.auth.service.AuthService;
import project.bind.MenToMen.domain.auth.dto.res.AccessTokenDto;
import project.bind.MenToMen.global.annotation.CheckToken;
import project.bind.MenToMen.global.response.DataResponse;

@Api(tags = "Auth-Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Value("${product.client-id}")
    private String clientId;
    @Value("${product.client-secret}")
    private String clientSecret;

    @ApiOperation(value = "Token 받기", notes = "AccessToken 과 RefreshToken 을 얻는다.")
    @PostMapping("/code")
    public ResponseEntity<DataResponse<TokenResponseDto>> resCode(@RequestBody DAuthClientRequestDto dAuthClientRequestDto){
        TokenResponseDto token = authService.getToken(new DAuthApiRequestDto(dAuthClientRequestDto.getCode(), clientId, clientSecret));
        return DataResponse.ok("인증 성공", token);
    }

    @CheckToken
    @ApiOperation(value = "AccessToken 재발급", notes = "RefreshToken 인증으로 AccessToken 재발급")
    @GetMapping("/refreshToken")
    public ResponseEntity<DataResponse<AccessTokenDto>> getAccessToken(@RequestAttribute("accessToken") AccessTokenDto accessTokenDto) {
        return DataResponse.ok("토큰 생성 성공", accessTokenDto);
    }

}

