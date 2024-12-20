package com.fullstack.shop.web.routine.controller;

import com.fullstack.shop.web.routine.entities.OrderDetails;
import com.fullstack.shop.web.routine.service.OrderDetailsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderDetails")
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<OrderDetails>> getOrderDetailsList(Pageable pageable) {
        Page<OrderDetails> detailsPage = orderDetailsService.getAllOrderDetails(pageable);
        if (detailsPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(detailsPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetails> getOrderDetails(@PathVariable("id") Integer id) {
        OrderDetails checkOrderDetailsId = orderDetailsService.getOrderDetailsById(id);
        if (checkOrderDetailsId == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(checkOrderDetailsId, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDetails> createOrderDetails(@RequestBody OrderDetails orderDetails) {
        OrderDetails checkCreated = orderDetailsService.saveOrUpdateOrderDetails(orderDetails);
        if (checkCreated == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(checkCreated, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetails> updateOrderDetails(@PathVariable("id") Integer id, @RequestBody OrderDetails orderDetails) {
        orderDetails.setId(id);
        OrderDetails checkUpdated = orderDetailsService.saveOrUpdateOrderDetails(orderDetails);
        if (checkUpdated == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(checkUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOrderDetails(@PathVariable("id") Integer id) {
        if (orderDetailsService.deleteOrderDetailsById(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
