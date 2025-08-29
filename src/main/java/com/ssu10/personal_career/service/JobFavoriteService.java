package com.ssu10.personal_career.service;

import com.ssu10.personal_career.domain.JobFavorite;
import com.ssu10.personal_career.repository.JobFavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobFavoriteService {

    private final JobFavoriteRepository repository;

    public JobFavoriteService(JobFavoriteRepository repository) {
        this.repository = repository;
    }

    public List<JobFavorite> getFavorites(Long memberId) {
        return repository.findByMemberId(memberId);
    }

    public void addFavorite(JobFavorite favorite) {
        if (!repository.existsByMemberIdAndJobId(favorite.getMemberId(), favorite.getJobId())) {
            repository.save(favorite);
        }
    }

    public void removeFavorite(Long memberId, String jobId) {
        repository.deleteByMemberIdAndJobId(memberId, jobId);
    }
}

