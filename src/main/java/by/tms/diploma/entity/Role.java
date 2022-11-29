package by.tms.diploma.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    HEAD_OF_DEPARTMENT,
    WORKER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
