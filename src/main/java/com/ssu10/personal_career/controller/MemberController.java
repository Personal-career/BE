package com.ssu10.personal_career.controller;

import com.ssu10.personal_career.config.CustomUserDetails;
import com.ssu10.personal_career.domain.Member;
import com.ssu10.personal_career.dto.LoginRequestDto;
import com.ssu10.personal_career.dto.SignupRequestDto;
import com.ssu10.personal_career.repository.MemberRepository;
import com.ssu10.personal_career.service.MemberService;
import com.ssu10.personal_career.util.JwtUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto dto) {
        memberService.signup(dto);
        return "회원가입 성공!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto, HttpSession session) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(dto.getMemberId(), dto.getPw());

            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            session.setAttribute("member_id", userDetails.getId());

            String token = JwtUtil.generateToken(userDetails.getUsername());
            System.out.println("로그인 성공: " + userDetails.getUsername());
            return ResponseEntity.ok(token);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }

    // 회원 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id) {
        Member member = memberService.findById(id);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(member);
    }

    // 회원 정보 수정 (기본 정보)
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(
            @PathVariable Long id,
            @RequestBody Member updatedMember
    ) {
        Member member = memberService.updateMember(id, updatedMember);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 희망 직무 수정
    @PutMapping("/{id}/roles")
    public ResponseEntity<?> updateRoles(
            @PathVariable Long id,
            @RequestBody List<String> roles
    ) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        member.setDesiredRoles(roles);
        memberRepository.save(member);

        return ResponseEntity.ok(member.getDesiredRoles());
    }

    // 기술 스택 수정
    @PutMapping("/{id}/skills")
    public ResponseEntity<?> updateSkills(
            @PathVariable Long id,
            @RequestBody List<String> skills
    ) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        member.setSkills(skills);
        memberRepository.save(member);

        return ResponseEntity.ok(member.getSkills());
    }

    @GetMapping("/me")
    public Member getCurrentMember(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long memberId = userDetails.getId();
        System.out.println("/me test : member_id: " + memberId);

        return memberService.findById(memberId);
    }
}