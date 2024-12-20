package com.fullstack.shop.web.routine.controller;

import com.fullstack.shop.web.routine.entities.Orders;
import com.fullstack.shop.web.routine.entities.Promotions;
import com.fullstack.shop.web.routine.service.OrdersService;
import com.fullstack.shop.web.routine.service.PromotionsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotions")
public class PromotionsController {
    private final PromotionsService promotionsService;

    public PromotionsController(PromotionsService promotionsService) {
        this.promotionsService = promotionsService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Promotions>> getPromotionsList(Pageable pageable) {
        Page<Promotions> promotionsPage = promotionsService.getPromotions(pageable);
        if (promotionsPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(promotionsPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promotions> getPromotions(@PathVariable("id") Integer id) {
        Promotions checkPromotionsId = promotionsService.getPromotionsById(id);
        if (checkPromotionsId == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(checkPromotionsId, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Promotions> createPromotions(@RequestBody Promotions promotions) {
        Promotions checkCreated = promotionsService.saveOrUpdatePromotions(promotions);
        if (checkCreated == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(checkCreated, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promotions> updatePromotions(@PathVariable("id") Integer id, @RequestBody Promotions promotions) {
        promotions.setId(id);
        Promotions checkUpdated = promotionsService.saveOrUpdatePromotions(promotions);
        if (checkUpdated == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(checkUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePromotions(@PathVariable("id") Integer id) {
        if (promotionsService.deletePromotionsById(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
