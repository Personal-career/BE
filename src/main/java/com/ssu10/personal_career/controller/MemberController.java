package com.ssu10.personal_career.controller;

import com.ssu10.personal_career.domain.Member;
import com.ssu10.personal_career.dto.LoginRequestDto;
import com.ssu10.personal_career.dto.SignupRequestDto;
import com.ssu10.personal_career.service.MemberService;
import com.ssu10.personal_career.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto dto) {
        memberService.signup(dto);
        return "회원가입 성공!";
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto) {
        Member member = memberService.login(dto); // 아이디/비번 체크
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
        String token = JwtUtil.generateToken(member.getMemberId());
        // 프론트로 토큰 반환
        return ResponseEntity.ok().body(token);
    }

}
