package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.entities.Orders;
import com.fullstack.shop.web.routine.repository.OrdersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Page<Orders> getAllOrders(Pageable pageable) {
        return ordersRepository.findAll(pageable);
    }

    public Orders getOrdersById(Integer id) {
        return ordersRepository.findById(id).orElse(null);
    }

    public Orders saveOrUpdateOrders(Orders orders) {
        return ordersRepository.save(orders);
    }

    public Boolean deleteOrdersById(Integer id) {
        boolean checkExist = ordersRepository.existsById(id);
        if (checkExist) {
            ordersRepository.deleteById(id);
        }
        return checkExist;
    }
}
