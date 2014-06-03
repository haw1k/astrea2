package ru.astrea.logic.service.jpa;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astrea.logic.entity.Consultation;
import ru.astrea.logic.repository.ConsultationRepository;
import ru.astrea.logic.service.ConsultationService;

import java.util.List;

@Service("ConsultationServiceImpl")
@Repository
@Transactional
public class ConsultationServiceImpl implements ConsultationService {
    @Autowired
    private ConsultationRepository consultationRepository;

    @Override
    public Consultation addConsultation(Consultation Consultation) {

        Consultation savedUser = consultationRepository.save(Consultation);
        return savedUser;
    }

    @Override
    public Consultation findById(Long id) {
        return consultationRepository.findOne(id);
    }

    @Override
    public Consultation findByName(String name) {
        return consultationRepository.findByName(name);
    }

    @Override
    public void deleteConsultation(Long id) {
        consultationRepository.delete(id);
    }

    @Override
    public Consultation editConsultation(Consultation Consultation) {
        return consultationRepository.save(Consultation);
    }

    @Transactional(readOnly=true)
    public List<Consultation> findAll() {
        return Lists.newArrayList(consultationRepository.findAll());
    }

    @Transactional(readOnly=true)
    public Page<Consultation> findAllByPage(Pageable pageable) {
        return consultationRepository.findAll(pageable);
    }
}
