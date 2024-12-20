package com.fullstack.shop.web.routine.controller;

import com.fullstack.shop.web.routine.entities.OrderDetails;
import com.fullstack.shop.web.routine.entities.Orders;
import com.fullstack.shop.web.routine.service.OrderDetailsService;
import com.fullstack.shop.web.routine.service.OrdersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Orders>> getOrdersList(Pageable pageable) {
        Page<Orders> ordersPage = ordersService.getAllOrders(pageable);
        if (ordersPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ordersPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrders(@PathVariable("id") Integer id) {
        Orders checkOrderId = ordersService.getOrdersById(id);
        if (checkOrderId == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(checkOrderId, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Orders> createOrders(@RequestBody Orders orders) {
        Orders checkCreated = ordersService.saveOrUpdateOrders(orders);
        if (checkCreated == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(checkCreated, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrders(@PathVariable("id") Integer id, @RequestBody Orders orders) {
        orders.setId(id);
        Orders checkUpdated = ordersService.saveOrUpdateOrders(orders);
        if (checkUpdated == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(checkUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOrders(@PathVariable("id") Integer id) {
        if (ordersService.deleteOrdersById(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
