package com.ssu10.personal_career.repository;

import com.ssu10.personal_career.domain.JobFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobFavoriteRepository extends JpaRepository<JobFavorite, Long> {
    List<JobFavorite> findByMemberId(Long memberId);
    boolean existsByMemberIdAndJobId(Long memberId, String jobId);
    void deleteByMemberIdAndJobId(Long memberId, String jobId);
}
