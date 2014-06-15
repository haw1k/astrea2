package ru.astrea.logic.service.jpa;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astrea.logic.entity.Review;
import ru.astrea.logic.repository.ReviewRepository;
import ru.astrea.logic.service.ReviewService;

import java.util.List;

@Service("ReviewServiceImpl")
@Repository
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review addReview(Review review) {
        Review savedReview = reviewRepository.save(review);
        return savedReview;
    }

    @Transactional(readOnly=true)
    public Review findById(Long id) {
        return reviewRepository.findOne(id);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.delete(id);
    }

    @Override
    public Review editReview(Review review) {
        return reviewRepository.save(review);
    }

    @Transactional(readOnly=true)
    public List<Review> findAll() {
        return Lists.newArrayList(reviewRepository.findAll());
    }

    @Override
    public List<Review> findByCriterion(String criterion) {
        return reviewRepository.findByCriterion(criterion);
    }

    @Transactional(readOnly=true)
    public Page<Review> findAllByPage(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }
}
