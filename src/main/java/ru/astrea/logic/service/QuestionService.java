package ru.astrea.logic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.astrea.logic.entity.Question;

import java.util.List;

public interface QuestionService {

    Question addQuestion(Question question);

    Question findById(Long id);

    void deleteQuestion(Long id);

    Question editQuestion(Question question);

    List<Question> findAll();

    Page<Question> findAllByPage(Pageable pageable);
}
