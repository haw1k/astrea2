package ru.astrea.logic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ru.astrea.logic.entity.TurnCategory;

import java.util.List;

public interface TurnCategoryRepository extends PagingAndSortingRepository<TurnCategory, Long>{
    @Query("SELECT c FROM TurnCategory c WHERE c.title LIKE %:criterion%")
    List<TurnCategory> findByCriterion(@Param("criterion") String criterion);
}
