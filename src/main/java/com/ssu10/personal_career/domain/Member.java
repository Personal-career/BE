package com.ssu10.personal_career.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Member {
    @Id
    Long member_id;
    String id;
    String pw;
    String name;
    Date birthdate;
    String phone;
    String email;
}
