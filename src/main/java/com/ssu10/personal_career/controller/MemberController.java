package com.ssu10.personal_career.controller;

import com.ssu10.personal_career.domain.Member;
import com.ssu10.personal_career.dto.LoginRequestDto;
import com.ssu10.personal_career.dto.SignupRequestDto;
import com.ssu10.personal_career.service.MemberService;
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
    public String login(@RequestBody LoginRequestDto dto) {
        Member member = memberService.login(dto);
        return member.getName() + "님, 로그인 성공!";
    }
}
