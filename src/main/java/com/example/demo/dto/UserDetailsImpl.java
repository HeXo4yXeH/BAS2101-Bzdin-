package com.example.demo.dto;

import com.example.demo.model.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static UserDetails fromApplicationUser(ApplicationUser user) {
        var authorities = user.getRoles().stream()
                .map(role -> role.getName().grantedAuthority())
                .flatMap(Set::stream)
                .toList();
        return new User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
