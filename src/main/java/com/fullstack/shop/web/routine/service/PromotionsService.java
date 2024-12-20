package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.entities.Promotions;
import com.fullstack.shop.web.routine.repository.PromotionsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PromotionsService {

    private final PromotionsRepository promotionsRepository;

    public PromotionsService(PromotionsRepository promotionsRepository) {
        this.promotionsRepository = promotionsRepository;
    }

    public Page<Promotions> getPromotions(Pageable pageable) {
        return promotionsRepository.findAll(pageable);
    }

    public Promotions getPromotionsById(Integer id) {
        return promotionsRepository.findById(id).orElse(null);
    }

    public Promotions saveOrUpdatePromotions(Promotions promotions) {
        return promotionsRepository.save(promotions);
    }

    public Boolean deletePromotionsById(Integer id) {
        boolean checkExists = promotionsRepository.existsById(id);
        if (checkExists) {
            promotionsRepository.deleteById(id);
        }
        return checkExists;
    }
}
