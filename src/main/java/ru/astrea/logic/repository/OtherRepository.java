package ru.astrea.logic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ru.astrea.logic.entity.Other;

import java.util.List;

public interface OtherRepository extends PagingAndSortingRepository<Other, Long> {
    @Query("SELECT t FROM Other t WHERE t.about LIKE %:criterion%")
    List<Other> findByCriterion(@Param("criterion") String criterion);
}
