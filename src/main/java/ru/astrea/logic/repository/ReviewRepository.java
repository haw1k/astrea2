package ru.astrea.logic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ru.astrea.logic.entity.Review;

import java.util.List;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {
    @Query("SELECT t FROM Review t WHERE t.name LIKE %:criterion% or t.review LIKE %:criterion%")
    List<Review> findByCriterion(@Param("criterion") String criterion);
}
