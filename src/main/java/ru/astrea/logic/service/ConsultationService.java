package ru.astrea.logic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.astrea.logic.entity.Consultation;

import java.util.List;

public interface ConsultationService {
    Consultation addConsultation(Consultation Consultation);

    Consultation findById(Long id);

    Consultation findByName(String name);

    void deleteConsultation(Long id);

    Consultation editConsultation(Consultation Consultation);

    List<Consultation> findAll();

    Page<Consultation> findAllByPage(Pageable pageable);
}
