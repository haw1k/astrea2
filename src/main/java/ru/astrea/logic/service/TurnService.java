package ru.astrea.logic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.astrea.logic.entity.Turn;

import java.util.List;

public interface TurnService {

    Turn addTurn(Turn turn);

    Turn findById(Long id);

    void deleteTurn(Long id);

    Turn editTurn(Turn turn);

    List<Turn> findAll();

    List<Turn> findByCriterion(String criterion);

    Page<Turn> findAllByPage(Pageable pageable);
}
