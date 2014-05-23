package ru.astrea.logic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.astrea.logic.entity.TurnCategory;

import java.util.List;

public interface TurnCategoryService  {

    TurnCategory editCategory(TurnCategory turnCategory);

    TurnCategory findById(long id);

    List<TurnCategory> findAll();

    List<TurnCategory> findByCriterion(String criterion);

    Page<TurnCategory> findAllByPage(Pageable pageable);
}
