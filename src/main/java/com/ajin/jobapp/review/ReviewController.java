package com.ajin.jobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.findAllReview(companyId), HttpStatus.OK);
    }
    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
        if(reviewService.createReview(companyId, review)){
            return new ResponseEntity<>("Review created successfully...",HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long id){
        Review review = reviewService.findReviewById(companyId,id);
        if(review != null){
            return new ResponseEntity<>(review,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/reviews/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,@PathVariable Long id,@RequestBody Review review){
        if(reviewService.updateReview(companyId,id,review)){
            return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review update failed",HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long id){
        if(reviewService.deleteReview(companyId, id)){
            return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Cant able to deleted Review",HttpStatus.NOT_FOUND);
        }
    }


}
