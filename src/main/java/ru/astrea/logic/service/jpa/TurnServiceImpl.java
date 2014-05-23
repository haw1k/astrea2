package ru.astrea.logic.service.jpa;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astrea.logic.entity.Turn;
import ru.astrea.logic.repository.TurnRepository;
import ru.astrea.logic.service.TurnService;

import java.util.List;

@Service("TurnServiceImpl")
@Repository
@Transactional
public class TurnServiceImpl implements TurnService {

    @Autowired
    private TurnRepository turnRepository;

    @Override
    public Turn addTurn(Turn turn) {
        Turn savedTurn = turnRepository.save(turn);
        return savedTurn;
    }

    @Transactional(readOnly=true)
    public Turn findById(Long id) {
        return turnRepository.findOne(id);
    }

    @Override
    public void deleteTurn(Long id) {
        System.out.println(id);;
        turnRepository.delete(id);
    }

    @Override
    public Turn editTurn(Turn turn) {
        return turnRepository.save(turn);
    }

    @Transactional(readOnly=true)
    public List<Turn> findAll() {
        return Lists.newArrayList(turnRepository.findAll());
    }

    @Transactional(readOnly=true)
    public List<Turn> findByCriterion(String criterion) {
        return turnRepository.findByCriterion(criterion);
    }

    @Transactional(readOnly=true)
    public Page<Turn> findAllByPage(Pageable pageable) {
        return turnRepository.findAll(pageable);
    }
}
