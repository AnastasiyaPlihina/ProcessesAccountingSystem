package by.tms.diploma.dto;

import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.Role;

import javax.persistence.*;
import java.util.Set;

public class UserDto {
    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private Department department;
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
