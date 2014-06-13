package ru.astrea.logic.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.astrea.logic.entity.Question;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
}
