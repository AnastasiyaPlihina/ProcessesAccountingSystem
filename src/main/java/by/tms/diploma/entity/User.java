package by.tms.diploma.entity;

import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity {
private String firstName;
private String secondName;
private String password;
private Role role;

    public User() {
    }

    public User(String firstName, String secondName, String password, Role role) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
