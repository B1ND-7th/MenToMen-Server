package project.bind.MenToMen.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.bind.MenToMen.domain.user.domain.Roles;
import project.bind.MenToMen.domain.user.domain.StdInfo;
import project.bind.MenToMen.domain.user.domain.User;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponseDto {

    private Long userId;
    private String name;
    private String email;
    private String profileImage;
    private StdInfo stdInfo;
    private Roles roles;

    public UserInfoResponseDto(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.profileImage = user.getProfileImage();
        this.stdInfo = user.getStdInfo();
        this.roles = user.getRoles();
    }
}
