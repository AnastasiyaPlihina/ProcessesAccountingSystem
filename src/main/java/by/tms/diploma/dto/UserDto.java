package by.tms.diploma.dto;

import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserDto {
    @NotBlank
    @Pattern(regexp = "[a-zA-Z]{3,16}")
    private String username;
    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{3,16}")
    private String password;
    @NotBlank
    @Pattern(regexp = "[a-zA-Z]{3,16}")
    private String firstName;
    @NotBlank
    @Pattern(regexp = "[a-zA-Z]{3,16}")
    private String secondName;
    @NotNull
    private Department department;
    @Size(min = 1)
    private Set<Role> roles;

    public UserDto() {
    }

    public UserDto(String username, String password, String firstName, String secondName, Department department, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.department = department;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
