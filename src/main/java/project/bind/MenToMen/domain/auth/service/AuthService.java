package project.bind.MenToMen.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.bind.MenToMen.domain.auth.dto.api.DAuthApiRequestDto;
import project.bind.MenToMen.domain.auth.dto.api.DAuthTokenResponseDto;
import project.bind.MenToMen.domain.auth.dto.api.DAuthUserInfoDataResponseDto;
import project.bind.MenToMen.domain.auth.dto.api.DAuthUserInfoResponseDto;
import project.bind.MenToMen.domain.auth.dto.res.TokenResponseDto;
import project.bind.MenToMen.domain.user.service.UserService;
import project.bind.MenToMen.global.config.jwt.JwtUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Value("${product.dauth}")
    private String dauthUrl;

    @Value("${product.open-dodam}")
    private String dodamOpenApiUrl;


    public TokenResponseDto getToken(DAuthApiRequestDto dAuthApiRequestDto) {

        HttpHeaders headers = new HttpHeaders();
        Map<String, String> data = new HashMap<String, String>();
        data.put("code", dAuthApiRequestDto.getCode());
        data.put("client_id", dAuthApiRequestDto.getClientId());
        data.put("client_secret", dAuthApiRequestDto.getClientSecret());

        HttpEntity<Map<String, String>> request = new HttpEntity<Map<String, String>>(data, headers);

        DAuthTokenResponseDto authTokenResponseDto = restTemplate.postForEntity(dauthUrl, request, DAuthTokenResponseDto.class).getBody();

        return getUserInfo(authTokenResponseDto);
    }

    public TokenResponseDto getUserInfo(DAuthTokenResponseDto dto) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer " + dto.getAccessToken());

        DAuthUserInfoResponseDto infoResponseDto = restTemplate.exchange(dodamOpenApiUrl, HttpMethod.GET, new HttpEntity<>(headers), DAuthUserInfoResponseDto.class).getBody();

        Optional.ofNullable(infoResponseDto.getData().getProfileImage()).ifPresent(
            s -> { if (s.contains(".null")) infoResponseDto.getData().setProfileImgNull(); });

        userService.save(infoResponseDto.getData().toEntity());

        return createUserToken(infoResponseDto.getData());
    }

    private TokenResponseDto createUserToken(DAuthUserInfoDataResponseDto DAuthUserInfoDataResponseDto) {
        return TokenResponseDto.builder()
                .accessToken(jwtUtil.generateAccessToken(DAuthUserInfoDataResponseDto.getEmail()))
                .refreshToken(jwtUtil.generateRefreshToken(DAuthUserInfoDataResponseDto.getEmail()))
                .build();
    }
}
