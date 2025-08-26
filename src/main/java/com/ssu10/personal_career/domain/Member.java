package com.ssu10.personal_career.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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

    /** 희망 직무 */
    @ElementCollection
    @CollectionTable(
            name = "member_roles",
            joinColumns = @JoinColumn(name = "member_id")
    )
    @Column(name = "role")
    private List<String> desiredRoles;

    /** 기술 스택 */
    @ElementCollection
    @CollectionTable(
            name = "member_skills",
            joinColumns = @JoinColumn(name = "member_id")
    )
    @Column(name = "skill")
    private List<String> skills;

    /** 프로필 사진 */
    private String profileImageUrl;
}
