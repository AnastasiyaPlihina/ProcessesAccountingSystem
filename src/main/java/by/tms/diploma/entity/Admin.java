package by.tms.diploma.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class Admin {
    @NotBlank(message = "Field must not be empty")
    @Pattern(message = "Enter correct nickname!", regexp = "admin")
    private String nickname;
    @NotBlank(message = "Field must not be empty")
    @Pattern(message = "Enter correct password!", regexp = "admin")
    private String password;

    public Admin() {
    }

    public Admin(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    private final Role role = Role.ADMIN;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
}
