package com.ssu10.personal_career.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "member_id")
    String memberId;
    String pw;
    String name;
    Date birthdate;
    String phone;
    String email;
}
