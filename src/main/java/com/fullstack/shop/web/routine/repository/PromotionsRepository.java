package com.fullstack.shop.web.routine.repository;

import com.fullstack.shop.web.routine.entities.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionsRepository extends JpaRepository<Promotions, Integer> {
}
