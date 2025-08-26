package com.ssu10.personal_career.repository;

import com.ssu10.personal_career.domain.Member;
import com.ssu10.personal_career.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findById(long id);

    @Query("SELECT p FROM Project p WHERE p.member_id = :memberId")
    List<Project> findByMember_id(Long memberId);
}
