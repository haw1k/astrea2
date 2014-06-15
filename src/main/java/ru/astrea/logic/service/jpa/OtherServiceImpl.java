package ru.astrea.logic.service.jpa;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astrea.logic.entity.Other;
import ru.astrea.logic.repository.OtherRepository;
import ru.astrea.logic.service.OtherService;

import java.util.List;

@Service("OtherServiceImpl")
@Repository
@Transactional
public class OtherServiceImpl implements OtherService {

    @Autowired
    OtherRepository otherRepository;

    @Transactional(readOnly=true)
    public Other findById(Long id) {
        return otherRepository.findOne(id);
    }

    @Transactional(readOnly=true)
    public List<Other> findAll() {
        return Lists.newArrayList(otherRepository.findAll());
    }

    @Override
    public void deleteOther(Long id) {
        otherRepository.delete(id);
    }

    @Override
    public Other editOther(Other other) {
        return otherRepository.save(other);
    }

    @Transactional(readOnly=true)
    public List<Other> findByCriterion(String criterion) {
        return otherRepository.findByCriterion(criterion);
    }
}
