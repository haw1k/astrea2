package ru.astrea.logic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ru.astrea.logic.entity.Turn;

import java.util.List;

public interface TurnRepository extends PagingAndSortingRepository<Turn, Long> {
    @Query("SELECT t FROM Turn t WHERE t.title LIKE %:criterion% or t.text LIKE %:criterion%")
    List<Turn> findByCriterion(@Param("criterion") String criterion);
}
