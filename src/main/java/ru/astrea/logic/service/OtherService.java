package ru.astrea.logic.service;

import ru.astrea.logic.entity.Other;

import java.util.List;

public interface OtherService {

    Other findById(Long id);

    List<Other> findAll();

    void deleteOther(Long id);

    Other editOther(Other other);

    List<Other> findByCriterion(String criterion);
}
