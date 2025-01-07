package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.dto.response.OrdersResponseDTO;
import com.fullstack.shop.web.routine.entities.Orders;
import com.fullstack.shop.web.routine.mapper.OrdersMapper;
import com.fullstack.shop.web.routine.repository.OrdersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrdersService {

	OrdersRepository ordersRepository;
	OrdersMapper ordersMapper;

	public Page<OrdersResponseDTO> getAllOrders(Pageable pageable) {
		Page<Orders> ordersPage = ordersRepository.findAll(pageable);
		List<OrdersResponseDTO> dtos = new ArrayList<>();
		for (Orders orders : ordersPage.getContent()) {
			OrdersResponseDTO dto = ordersMapper.entityToDto(orders);
			dtos.add(dto);
		}
		return new PageImpl<>(dtos, pageable, ordersPage.getTotalElements());
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
