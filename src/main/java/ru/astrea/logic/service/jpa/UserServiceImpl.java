package ru.astrea.logic.service.jpa;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astrea.logic.entity.User;
import ru.astrea.logic.repository.UserRepository;
import ru.astrea.logic.service.UserService;

import java.util.List;

@Service("UserServiceImpl")
@Repository
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User editUser(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly=true)
    public List<User> findAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Transactional(readOnly=true)
    public Page<User> findAllByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
