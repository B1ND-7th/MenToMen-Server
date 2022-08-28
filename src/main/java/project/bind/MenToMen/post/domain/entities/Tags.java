package project.bind.MenToMen.post.domain.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Tags {
    FRONTEND("front"),
    BACKEND("back"),
    IOS("ios"),
    ANDROID("android"),
    DESIGN("design");

    private final String tagName;
}
