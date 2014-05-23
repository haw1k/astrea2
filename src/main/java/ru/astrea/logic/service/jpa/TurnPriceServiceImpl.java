package ru.astrea.logic.service.jpa;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astrea.logic.entity.TurnPrice;
import ru.astrea.logic.repository.TurnPriceRepository;
import ru.astrea.logic.service.TurnPriceService;

import java.util.List;

@Service("TurnPriceServiceImpl")
@Repository
@Transactional
public class TurnPriceServiceImpl implements TurnPriceService {

    @Autowired
    private TurnPriceRepository turnPriceRepository;

    @Override
    public TurnPrice addTurnPrice(TurnPrice turnPrice) {
        TurnPrice savedTurnPrice = turnPriceRepository.save(turnPrice);
        return savedTurnPrice;
    }

    @Transactional(readOnly=true)
    public TurnPrice findById(Long id) {
        return turnPriceRepository.findOne(id);
    }

    @Override
    public void deleteTurnPrice(Long id) {
        turnPriceRepository.delete(id);
    }

    @Override
    public TurnPrice editTurnPrice(TurnPrice turnPrice) {
        return turnPriceRepository.save(turnPrice);
    }

    @Transactional(readOnly=true)
    public List<TurnPrice> findAll() {
        return Lists.newArrayList(turnPriceRepository.findAll());
    }

    @Transactional(readOnly=true)
    public List<TurnPrice> findByCriterion(String criterion) {
        return turnPriceRepository.findByCriterion(criterion);
    }

    @Transactional(readOnly=true)
    public Page<TurnPrice> findAllByPage(Pageable pageable) {
        return turnPriceRepository.findAll(pageable);
    }
}
