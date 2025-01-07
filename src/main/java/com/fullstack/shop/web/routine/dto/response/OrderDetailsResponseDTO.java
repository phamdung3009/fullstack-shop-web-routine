package com.fullstack.shop.web.routine.dto.response;

import com.fullstack.shop.web.routine.entities.Orders;
import com.fullstack.shop.web.routine.entities.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailsResponseDTO {
	Integer id;
	Double price;
	Integer quantity;
	Orders orders;
	Product product;
}
