package app.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoleEnum implements GrantedAuthority {
    ROLE_ADMIN, ROLE_USER;


    @Override
    public String getAuthority() {
        return name();
    }
}
