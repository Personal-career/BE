package com.ssu10.personal_career.service;

import com.ssu10.personal_career.domain.Member;
import com.ssu10.personal_career.dto.LoginRequestDto;
import com.ssu10.personal_career.dto.SignupRequestDto;
import com.ssu10.personal_career.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    //회원가입
    public void signup(SignupRequestDto dto) {
        // 아이디/이메일 중복 체크
        if (memberRepository.findByMemberId(dto.getMemberId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        if (memberRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        Member member = new Member();
        member.setMemberId(dto.getMemberId());
        member.setPw(passwordEncoder.encode(dto.getPw()));
        member.setName(dto.getName());
        member.setBirthdate(dto.getBirthdate());
        member.setPhone(dto.getPhone());
        member.setEmail(dto.getEmail());

        System.out.println(dto.getMemberId());
        System.out.println(dto.getEmail());

        memberRepository.save(member);
    }

    //로그인
    public Member login(LoginRequestDto dto) {
        Member member = memberRepository.findByMemberId(dto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        if (!passwordEncoder.matches(dto.getPw(), member.getPw())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return member; // 로그인 성공 시 회원 정보 반환
    }
}
