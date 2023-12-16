package com.springteam.TechProduct.service;

import com.springteam.TechProduct.entity.UserEntity;

import java.util.Optional;

public interface UserEntityService {
    UserEntity getUserByUserName(String userName);
    UserEntity getUserByEmail(String email);
    Boolean isExistsByUserName(String userName);
    Boolean isExistsByEmail(String email);

    void addNewUser(UserEntity user);
}
