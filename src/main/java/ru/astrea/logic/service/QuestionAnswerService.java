package ru.astrea.logic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.astrea.logic.entity.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerService {

    QuestionAnswer addQuestionAnswer(QuestionAnswer questionAnswer);

    QuestionAnswer findById(Long id);

    void deleteQuestionAnswer(Long id);

    QuestionAnswer editQuestionAnswer(QuestionAnswer questionAnswer);

    List<QuestionAnswer> findAll();

    List<QuestionAnswer> findByCriterion(String criterion);

    Page<QuestionAnswer> findAllByPage(Pageable pageable);
}
