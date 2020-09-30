package ru.shamma.service;


import ru.shamma.dao.RoleDao;
import ru.shamma.persist.RoleRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class RoleService implements Serializable {

    @Inject
    private RoleRepository roleRepository;

    @TransactionAttribute
    public List<RoleDao> getAllRoles() {
        return roleRepository.getAllRoles().stream()
                .map(RoleDao::new)
                .collect(Collectors.toList());
    }
}
