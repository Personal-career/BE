package com.ssu10.personal_career.repository;

import com.ssu10.personal_career.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
