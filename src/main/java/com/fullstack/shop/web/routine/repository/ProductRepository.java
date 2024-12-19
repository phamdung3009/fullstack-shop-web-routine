package com.fullstack.shop.web.routine.repository;

import com.fullstack.shop.web.routine.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
