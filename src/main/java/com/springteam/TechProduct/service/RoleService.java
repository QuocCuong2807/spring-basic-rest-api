package com.springteam.TechProduct.service;

import com.springteam.TechProduct.entity.Role;

import java.util.Optional;

public interface RoleService {
    Role getRoleByName(String name);
    Boolean isExistsByName(String name);
}
