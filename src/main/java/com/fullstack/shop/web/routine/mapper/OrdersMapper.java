package com.fullstack.shop.web.routine.mapper;

import com.fullstack.shop.web.routine.dto.request.OrdersRequestDTO;
import com.fullstack.shop.web.routine.dto.response.OrderDetailsResponseDTO;
import com.fullstack.shop.web.routine.dto.response.OrdersResponseDTO;
import com.fullstack.shop.web.routine.entities.OrderDetails;
import com.fullstack.shop.web.routine.entities.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrdersMapper {
	OrdersResponseDTO entityToDto(Orders orders);
	Orders dtoToEntity(OrdersRequestDTO dto);
	void update(OrdersRequestDTO dto,@MappingTarget Orders orders);
}
