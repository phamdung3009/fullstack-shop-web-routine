package com.fullstack.shop.web.routine.repository;

import com.fullstack.shop.web.routine.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.nameProduct LIKE %:name%")
    Page<Product> searchByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category.id = :cat_id")
    Page<Product> searchByCatId(@Param("cat_id") Integer cat_id, Pageable pageable);
}
