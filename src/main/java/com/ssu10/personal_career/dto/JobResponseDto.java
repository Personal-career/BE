package com.ssu10.personal_career.dto;

import com.ssu10.personal_career.domain.Job;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobResponseDto {

    private String empSeqno;
    private String empWantedTitle;
    private String empBusiNm;
    private String empWantedTypeNm;
    private String empWantedStdt;
    private String empWantedEndt;
    private String empWantedHomepgDetail;
    private String regLogImgNm;
    private String location;

    public static JobResponseDto fromEntity(Job job) {
        return JobResponseDto.builder()
                .empSeqno(job.getJobId())
                .empWantedTitle(job.getJobTitle())
                .empBusiNm(job.getCompanyName())
                .empWantedTypeNm(job.getEmploymentType())
                .empWantedStdt(job.getStartDate() != null ? job.getStartDate().toString() : null)
                .empWantedEndt(job.getEndDate() != null ? job.getEndDate().toString() : null)
                .empWantedHomepgDetail(job.getApplyLink())
                .regLogImgNm(job.getCompanyLogo())
                .location(job.getCompanyType())
                .build();
    }
}
