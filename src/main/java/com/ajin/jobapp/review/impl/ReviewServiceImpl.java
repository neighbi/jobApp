package com.ajin.jobapp.review.impl;

import com.ajin.jobapp.company.Company;
import com.ajin.jobapp.company.CompanyService;
import com.ajin.jobapp.review.Review;
import com.ajin.jobapp.review.ReviewRepository;
import com.ajin.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {

    private CompanyService companyService;
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(CompanyService companyService, ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> findAllReview(Long id) {
        return reviewRepository.findByCompanyId(id);
    }

    @Override
    public Review findReviewById(Long companyId, Long id) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
//        for (Review  review:reviews){
//            if(review.getId().equals(id))
//                return review;
//        }
//        return null;

        return reviews.stream()
                .filter(review -> review.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        try{
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        catch (Exception e){

            return false;
        }
    }

    @Override
    public boolean updateReview(Long companyId, Long id, Review review) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        Review review1 = reviews.stream()
                .filter(reviews1 -> reviews1.getId().equals(id))
                .findFirst()
                .orElse(null);
        if(review1 != null){
            review1.setTitle(review.getTitle());
            review1.setDescription(review.getDescription());
            review1.setRating(review.getRating());
//            review1.setCompany(review.getCompany());
            reviewRepository.save(review1);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long id) {
        try {
            Review review = reviewRepository.getById(id);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompany(company,companyId);
            reviewRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
