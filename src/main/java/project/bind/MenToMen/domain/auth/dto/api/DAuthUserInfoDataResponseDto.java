package project.bind.MenToMen.domain.auth.dto.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.bind.MenToMen.domain.user.domain.Roles;
import project.bind.MenToMen.domain.user.domain.StdInfo;
import project.bind.MenToMen.domain.user.domain.User;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DAuthUserInfoDataResponseDto {

    private int grade;
    private int room;
    private int number;
    private String name;
    private String profileImage;
    private String email;

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .profileImage(profileImage)
                .stdInfo(new StdInfo(grade, room, number))
                .roles(Roles.USER)
                .build();
    }
}
