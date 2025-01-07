package com.fullstack.shop.web.routine.mapper;

import com.fullstack.shop.web.routine.dto.response.OrderDetailsResponseDTO;
import com.fullstack.shop.web.routine.dto.response.ProductResponseDTO;
import com.fullstack.shop.web.routine.entities.OrderDetails;
import com.fullstack.shop.web.routine.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {
	OrderDetailsResponseDTO entityToDto(OrderDetails orderDetails);
}
