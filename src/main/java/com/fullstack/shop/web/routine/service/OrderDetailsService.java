package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.entities.OrderDetails;
import com.fullstack.shop.web.routine.repository.OrderDetailsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public Page<OrderDetails> getAllOrderDetails(Pageable pageable) {
        return orderDetailsRepository.findAll(pageable);
    }

    public OrderDetails getOrderDetailsById(Integer id) {
        return orderDetailsRepository.findById(id).orElse(null);
    }

    public OrderDetails saveOrUpdateOrderDetails(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    public Boolean deleteOrderDetailsById(Integer id) {
        boolean checkExists = orderDetailsRepository.existsById(id);
        if (checkExists) {
            orderDetailsRepository.deleteById(id);
        }
        return checkExists;
    }
}
