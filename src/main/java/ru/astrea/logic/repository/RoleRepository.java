package ru.astrea.logic.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.astrea.logic.entity.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
}
