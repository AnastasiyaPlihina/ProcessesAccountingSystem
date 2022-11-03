package by.tms.diploma.entity;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class User extends AbstractEntity {
    private String firstName;
    private String secondName;
    private String password;
    @ManyToOne
    private Department department;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String firstName, String secondName, String password, Department department, Role role) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
