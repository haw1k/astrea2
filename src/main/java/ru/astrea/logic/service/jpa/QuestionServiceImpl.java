package ru.astrea.logic.service.jpa;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ru.astrea.logic.entity.Question;
import ru.astrea.logic.repository.QuestionRepository;
import ru.astrea.logic.service.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        Question savedQuestion = questionRepository.save(question);
        return savedQuestion;
    }

    @Transactional(readOnly=true)
    public Question findById(Long id) {
        return questionRepository.findOne(id);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.delete(id);
    }

    @Override
    public Question editQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Transactional(readOnly=true)
    public List<Question> findAll() {
        return Lists.newArrayList(questionRepository.findAll());
    }

    @Transactional(readOnly=true)
    public Page<Question> findAllByPage(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }
}
