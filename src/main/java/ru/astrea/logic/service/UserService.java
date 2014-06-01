package ru.astrea.logic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.astrea.logic.entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User findById(Long id);

    void deleteUser(Long id);

    User editUser(User user);

    List<User> findAll();

    Page<User> findAllByPage(Pageable pageable);
}
