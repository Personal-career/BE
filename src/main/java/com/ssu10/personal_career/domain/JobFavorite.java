package com.ssu10.personal_career.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_favorite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 관심기업 등록한 회원
    @Column(name = "member_id")
    private Long memberId;

    // 참조용: jobs 테이블의 id
    @Column(name = "job_id")
    private String jobId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_logo")
    private String companyLogo;

    @Column(name = "apply_link")
    private String applyLink;
}

