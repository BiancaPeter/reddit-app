package com.reddit.app.service;

import com.reddit.app.model.Role;
import com.reddit.app.model.RoleType;
import com.reddit.app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role addRole(RoleType roleType) {
        Role newRole = new Role();
        newRole.setRoleType(roleType);
        newRole.setUserList(new ArrayList<>());
        return roleRepository.save(newRole);
    }
}
