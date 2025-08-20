package com.ssu10.personal_career.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Recruit {
    @Id
    Long recruit_id;

    Company company;
    String name;
    String content;
}
