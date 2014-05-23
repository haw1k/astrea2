package ru.astrea.logic.service.jpa;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astrea.logic.entity.TurnCategory;
import ru.astrea.logic.repository.TurnCategoryRepository;
import ru.astrea.logic.service.TurnCategoryService;

import java.util.List;

@Service("TurnCategoryServiceImpl")
@Repository
@Transactional
public class TurnCategoryServiceImpl implements TurnCategoryService {

    @Autowired
    TurnCategoryRepository turnCategoryRepository;

    @Override
    public TurnCategory editCategory(TurnCategory turnCategory) {
        return turnCategoryRepository.save(turnCategory);
    }

    @Override
    public TurnCategory findById(long id) {
        return turnCategoryRepository.findOne(id);
    }

    @Override
    public List<TurnCategory> findAll() {
        return Lists.newArrayList(turnCategoryRepository.findAll());
    }

    @Override
    public List<TurnCategory> findByCriterion(String criterion) {
        return turnCategoryRepository.findByCriterion(criterion);
    }

    @Transactional(readOnly = true)
    public Page<TurnCategory> findAllByPage(Pageable pageable) {
        return turnCategoryRepository.findAll(pageable);
    }
}
