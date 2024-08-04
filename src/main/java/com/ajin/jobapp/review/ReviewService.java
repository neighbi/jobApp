package com.ajin.jobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> findAllReview(Long id);
    Review findReviewById(Long companyId, Long id);
    boolean createReview(Long id, Review review);
    boolean updateReview(Long CompanyId,Long id,Review review);
    boolean deleteReview(Long companyId, Long id);
}
