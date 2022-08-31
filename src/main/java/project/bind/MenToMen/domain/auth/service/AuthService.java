package project.bind.MenToMen.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.bind.MenToMen.domain.auth.dto.api.DAuthApiRequestDto;
import project.bind.MenToMen.domain.auth.dto.api.DAuthTokenResponseDto;
import project.bind.MenToMen.domain.auth.dto.api.DAuthUserInfoDataResponseDto;
import project.bind.MenToMen.domain.auth.dto.api.DAuthUserInfoResponseDto;
import project.bind.MenToMen.domain.auth.dto.res.DAuthClientResponseDto;
import project.bind.MenToMen.domain.auth.dto.res.TokenResponseDto;
import project.bind.MenToMen.domain.user.service.UserService;
import project.bind.MenToMen.global.config.jwt.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public DAuthClientResponseDto getLoginUrl(String clientId, String redirectUrl) {
        return new DAuthClientResponseDto("http://dauth.b1nd.com/login?client_id=" + clientId + "&redirect_uri=" + redirectUrl);
    }

    public TokenResponseDto getToken(DAuthApiRequestDto dAuthApiRequestDto) {

        HttpHeaders headers = new HttpHeaders();
        Map<String, String> data = new HashMap<String, String>();
        data.put("code", dAuthApiRequestDto.getCode());
        data.put("client_id", dAuthApiRequestDto.getClientId());
        data.put("client_secret", dAuthApiRequestDto.getClientSecret());

        HttpEntity<Map<String, String>> request = new HttpEntity<Map<String, String>>(data, headers);

        String url = "http://dauth.b1nd.com/api/token";
        DAuthTokenResponseDto authTokenResponseDto = restTemplate.postForEntity(url, request, DAuthTokenResponseDto.class).getBody();

        return getUserInfo(authTokenResponseDto);
    }

    public TokenResponseDto getUserInfo(DAuthTokenResponseDto dto) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer " + dto.getAccessToken());

        String url = "http://open.dodam.b1nd.com/api/user";

        DAuthUserInfoResponseDto infoResponseDto = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), DAuthUserInfoResponseDto.class).getBody();
        userService.save(infoResponseDto.getData().toEntity());

        return getUserToken(infoResponseDto.getData());
    }

    public TokenResponseDto getUserToken(DAuthUserInfoDataResponseDto DAuthUserInfoDataResponseDto) {
        return TokenResponseDto.builder()
                .accessToken(jwtUtil.generateAccessToken(DAuthUserInfoDataResponseDto.getEmail()))
                .refreshToken(jwtUtil.generateRefreshToken(DAuthUserInfoDataResponseDto.getEmail()))
                .build();
    }
}
