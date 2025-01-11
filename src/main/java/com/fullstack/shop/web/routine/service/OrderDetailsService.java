package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.dto.request.OrderDetailsRequestDTO;
import com.fullstack.shop.web.routine.dto.response.OrderDetailsResponseDTO;
import com.fullstack.shop.web.routine.entities.OrderDetails;
import com.fullstack.shop.web.routine.mapper.OrderDetailsMapper;
import com.fullstack.shop.web.routine.repository.OrderDetailsRepository;
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
public class OrderDetailsService {

	OrderDetailsRepository orderDetailsRepository;
	OrderDetailsMapper orderDetailsMapper;


	public Page<OrderDetailsResponseDTO> getAllOrderDetails(Pageable pageable) {
		Page<OrderDetails> orderDetailsPage = orderDetailsRepository.findAll(pageable);
		List<OrderDetailsResponseDTO> dtos = new ArrayList<>();
		for (OrderDetails orderDetails : orderDetailsPage.getContent()) {
			OrderDetailsResponseDTO dto = orderDetailsMapper.entityToDto(orderDetails);
			dtos.add(dto);
		}
		return new PageImpl<>(dtos, pageable, orderDetailsPage.getTotalElements());
	}

	public OrderDetails getOrderDetailsById(Integer id) {
		return orderDetailsRepository.findById(id).orElse(null);
	}

	public OrderDetails saveOrderDetails(OrderDetailsRequestDTO requestDTO) {
		OrderDetails orderDetails = orderDetailsMapper.dtoToEntity(requestDTO);
		return orderDetailsRepository.save(orderDetails);
	}

	public OrderDetails updateOrderDetails(Integer id, OrderDetailsRequestDTO requestDTO) {
		OrderDetails orderDetails = orderDetailsRepository.findById(id).orElse(null);
		orderDetailsMapper.updateDtoToEntity(requestDTO, orderDetails);
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
