package ru.astrea.logic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ru.astrea.logic.entity.TurnPrice;

import java.util.List;

public interface TurnPriceRepository extends PagingAndSortingRepository<TurnPrice, Long> {
    @Query("SELECT t FROM TurnPrice t WHERE t.turn LIKE %:criterion% or t.price LIKE %:criterion%")
    List<TurnPrice> findByCriterion(@Param("criterion") String criterion);
}