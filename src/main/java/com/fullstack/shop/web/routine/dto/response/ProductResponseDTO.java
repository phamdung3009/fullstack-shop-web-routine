package com.fullstack.shop.web.routine.dto.response;

import com.fullstack.shop.web.routine.entities.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDTO {
	Integer id;
	String nameProduct;
	double price;
	String color;
	String title;
	Category category;
	Integer quantity;
	String description;
}
