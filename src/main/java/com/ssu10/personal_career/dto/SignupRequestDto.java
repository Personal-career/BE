package com.ssu10.personal_career.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SignupRequestDto {
    @Column(name = "member_id")
    private String memberId;   // member_id와 매핑
    private String pw;
    private String name;
    private Date birthdate;
    private String phone;
    private String email;
}
