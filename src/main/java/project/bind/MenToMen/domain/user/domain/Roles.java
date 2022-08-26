package project.bind.MenToMen.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Roles {

    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
