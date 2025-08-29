package com.ssu10.personal_career.config;

import com.ssu10.personal_career.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CustomUserDetails implements UserDetails {

    private Long id;
    private String username; // Member.memberId
    private String password; // Member.pw
    private List<String> desiredRoles;
    private List<String> skills;
    private String profileImageUrl;

    private List<GrantedAuthority> authorities;

    public CustomUserDetails(Member member) {
        this.id = member.getId();
        this.username = member.getMemberId();
        this.password = member.getPw();
        this.desiredRoles = member.getDesiredRoles();
        this.skills = member.getSkills();
        this.profileImageUrl = member.getProfileImageUrl();
        // DB에 저장된 희망 직무를 권한으로 변환
        this.authorities = desiredRoles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // UserDetails 기본 필드 구현
    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return username; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
