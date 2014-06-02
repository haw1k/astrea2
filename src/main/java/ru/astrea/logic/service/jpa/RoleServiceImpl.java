package ru.astrea.logic.service.jpa;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astrea.logic.entity.Role;
import ru.astrea.logic.repository.RoleRepository;
import ru.astrea.logic.service.RoleService;

import java.util.List;

@Service("RoleServiceImpl")
@Repository
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role addRole(Role Role) {
        Role savedRole = roleRepository.save(Role);
        return savedRole;
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findOne(id);
    }


    @Override
    public void deleteRole(Long id) {
        roleRepository.delete(id);
    }

    @Override
    public Role editRole(Role Role) {
        return roleRepository.save(Role);
    }

    @Transactional(readOnly=true)
    public List<Role> findAll() {
        return Lists.newArrayList(roleRepository.findAll());
    }

    @Transactional(readOnly=true)
    public Page<Role> findAllByPage(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }
}
