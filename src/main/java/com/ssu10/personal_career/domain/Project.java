package com.ssu10.personal_career.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Project {
    //내 프로젝트 저장
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "member_id")
    Long member_id;
    String name;
    int period;
    String type;
    @Column(name = "description")
    private String description;
    String techStack;
}
