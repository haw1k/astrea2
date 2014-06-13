package ru.astrea.logic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ru.astrea.logic.entity.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerRepository extends PagingAndSortingRepository<QuestionAnswer, Long> {
    @Query("SELECT t FROM QuestionAnswer t WHERE t.question LIKE %:criterion% or t.answer LIKE %:criterion% or t.name LIKE %:criterion% or t.email LIKE %:criterion%" )
    List<QuestionAnswer> findByCriterion(@Param("criterion") String criterion);
}
