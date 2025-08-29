package com.ssu10.personal_career.controller;
import com.ssu10.personal_career.domain.JobFavorite;
import com.ssu10.personal_career.service.JobFavoriteService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs/favorite")
public class JobFavoriteController {
    private final JobFavoriteService service;
    public JobFavoriteController(JobFavoriteService service
    ) { this.service = service; }

// 관심기업 조회
@GetMapping public List<JobFavorite> getFavorites() {
        Long memberId = 1L; // 임시: 로그인 유저 ID
return service.getFavorites(memberId); }
// 관심기업 등록

    @PostMapping public void addFavorite(@RequestBody JobFavorite favorite) {
        Long memberId = 1L;// 임시
        System.out.println("POST로 받은 favorite 객체: " + favorite);
        favorite.setMemberId(memberId);
        service.addFavorite(favorite); }

// 관심기업 삭제
@DeleteMapping("/{jobId}")
public void removeFavorite(@PathVariable String jobId) {
        Long memberId = 1L; // 임시
    service.removeFavorite(memberId, jobId); } }