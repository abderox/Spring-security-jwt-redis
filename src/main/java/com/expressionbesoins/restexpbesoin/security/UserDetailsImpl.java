package com.expressionbesoins.restexpbesoin.security;

import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailsImpl implements UserDetails {

    private final String username;
    private final String password;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;
    private static final long serialVersionUID = 1L;
    private User user;
    private Set<String> roles;

    public UserDetailsImpl(User user , String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }


    public UserDetailsImpl(User user, Collection<? extends GrantedAuthority> authorities, Set<String> roles) {
        this.user = user;
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.authorities = authorities;
        this.roles = roles;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if ( roles != null ) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
                System.out.println("roles working : "+role);
            }
        }
        else {
            authorities.add(new SimpleGrantedAuthority(PrivilegeEnum.ROLE_USER.toString()));
            System.out.println("roles empty  : "+ PrivilegeEnum.ROLE_USER);
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
