package ru.astrea.logic.service.jpa;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astrea.logic.entity.QuestionAnswer;
import ru.astrea.logic.repository.QuestionAnswerRepository;
import ru.astrea.logic.service.QuestionAnswerService;

import java.util.List;

@Service("QuestionAnswerServiceImpl")
@Repository
@Transactional
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @Override
    public QuestionAnswer addQuestionAnswer(QuestionAnswer questionAnswer) {
        QuestionAnswer savedQuestionAnswer = questionAnswerRepository.save(questionAnswer);
        return savedQuestionAnswer;
    }

    @Transactional(readOnly=true)
    public QuestionAnswer findById(Long id) {
        return questionAnswerRepository.findOne(id);
    }

    @Override
    public void deleteQuestionAnswer(Long id) {
        questionAnswerRepository.delete(id);
    }

    @Override
    public QuestionAnswer editQuestionAnswer(QuestionAnswer questionAnswer) {
        return questionAnswerRepository.save(questionAnswer);
    }

    @Transactional(readOnly=true)
    public List<QuestionAnswer> findAll() {
        return Lists.newArrayList(questionAnswerRepository.findAll());
    }

    @Transactional(readOnly=true)
    public List<QuestionAnswer> findByCriterion(String criterion) {
        return questionAnswerRepository.findByCriterion(criterion);
    }

    @Transactional(readOnly=true)
    public Page<QuestionAnswer> findAllByPage(Pageable pageable) {
        return questionAnswerRepository.findAll(pageable);
    }
}
