package com.springteam.TechProduct.repository;

import com.springteam.TechProduct.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserName(String userName);
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);
}
