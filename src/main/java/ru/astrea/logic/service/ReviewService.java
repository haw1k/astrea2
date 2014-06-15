package ru.astrea.logic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.astrea.logic.entity.Review;

import java.util.List;

public interface ReviewService {

    Review addReview(Review review);

    Review findById(Long id);

    void deleteReview(Long id);

    Review editReview(Review review);

    List<Review> findAll();

    List<Review> findByCriterion(String criterion);

    Page<Review> findAllByPage(Pageable pageable);
}
