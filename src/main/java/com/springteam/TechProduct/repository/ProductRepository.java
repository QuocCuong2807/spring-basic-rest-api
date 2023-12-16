package com.springteam.TechProduct.repository;

import com.springteam.TechProduct.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findProductById(int id);
    Page<Product> findProductsByName(String name, Pageable pageable);
}
