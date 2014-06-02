package ru.astrea.logic.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.astrea.logic.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
}
