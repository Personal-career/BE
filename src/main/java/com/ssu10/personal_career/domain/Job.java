package com.ssu10.personal_career.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "employment_type")
    private String employmentType;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "company_type")
    private String companyType;

    @Column(name = "company_logo")
    private String companyLogo;

    @Column(name = "apply_link")
    private String applyLink;

    @Column(name = "job_id")
    private String jobId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
