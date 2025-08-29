package com.ssu10.personal_career.config;

import com.ssu10.personal_career.config.CustomUserDetails;
import com.ssu10.personal_career.domain.Member;
import com.ssu10.personal_career.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다. id=" + memberId));

        System.out.println("login 시도 ID: " + memberId);
        System.out.println("DB 비밀번호: " + member.getPw());

        return new CustomUserDetails(member);
    }
}
