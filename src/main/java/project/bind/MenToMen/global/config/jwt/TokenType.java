package project.bind.MenToMen.global.config.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenType {

    AccessToken("accessToken"),
    RefreshToken("refreshToken");

    private final String values;
}
