package com.ssu10.personal_career.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String memberId;
    private String pw;
}
