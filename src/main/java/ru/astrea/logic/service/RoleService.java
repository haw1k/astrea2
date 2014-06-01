package ru.astrea.logic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.astrea.logic.entity.Role;

import java.util.List;

public interface RoleService {
    Role addRole(Role role);

    Role findById(Long id);

    void deleteRole(Long id);

    Role editRole(Role role);

    List<Role> findAll();

    Page<Role> findAllByPage(Pageable pageable);
}
