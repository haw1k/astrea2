package ru.astrea.logic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.astrea.logic.entity.TurnPrice;

import java.util.List;

public interface TurnPriceService {
    TurnPrice addTurnPrice(TurnPrice turnPrice);

    TurnPrice findById(Long id);

    void deleteTurnPrice (Long id);

    TurnPrice editTurnPrice(TurnPrice turnPrice);

    List<TurnPrice> findAll();

    List<TurnPrice> findByCriterion(String criterion);

    Page<TurnPrice> findAllByPage(Pageable pageable);
}
