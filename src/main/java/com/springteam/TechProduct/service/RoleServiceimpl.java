package com.springteam.TechProduct.service;

import com.springteam.TechProduct.entity.Role;
import com.springteam.TechProduct.exception.RoleNotFoundException;
import com.springteam.TechProduct.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceimpl implements RoleService{

    private RoleRepository repository;

    @Autowired
    public RoleServiceimpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role getRoleByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new RoleNotFoundException("name of role is not found"));
    }

    @Override
    public Boolean isExistsByName(String name) {
        return repository.existsByName(name);
    }
}
