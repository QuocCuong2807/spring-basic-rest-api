package com.springteam.TechProduct.service;

import com.springteam.TechProduct.entity.UserEntity;
import com.springteam.TechProduct.exception.UserNotFoundException;
import com.springteam.TechProduct.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEntityServiceimpl implements UserEntityService{

    private UserRepository repository;

    @Autowired
    public UserEntityServiceimpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserEntity getUserByUserName(String userName) {
        return repository.findByUserName(userName).orElseThrow(() -> new UserNotFoundException("username not found"));
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("email not found"));
    }

    @Override
    public Boolean isExistsByUserName(String userName) {
        return repository.existsByUserName(userName);
    }

    @Override
    public Boolean isExistsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    @Transactional
    public void addNewUser(UserEntity user) {
        repository.save(user);
    }
}
