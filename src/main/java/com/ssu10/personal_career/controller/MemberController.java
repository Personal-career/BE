package com.ssu10.personal_career.controller;

import com.ssu10.personal_career.domain.Member;
import com.ssu10.personal_career.dto.LoginRequestDto;
import com.ssu10.personal_career.dto.SignupRequestDto;
import com.ssu10.personal_career.repository.MemberRepository;
import com.ssu10.personal_career.service.MemberService;
import com.ssu10.personal_career.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public MemberController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto dto) {
        memberService.signup(dto);
        return "회원가입 성공!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto) {
        Member member = memberService.login(dto);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
        String token = JwtUtil.generateToken(member.getMemberId());
        return ResponseEntity.ok(token);
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
}