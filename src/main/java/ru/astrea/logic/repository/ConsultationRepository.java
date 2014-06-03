package ru.astrea.logic.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.astrea.logic.entity.Consultation;

public interface ConsultationRepository extends PagingAndSortingRepository<Consultation, Long> {
    Consultation findByName(String name);
}
