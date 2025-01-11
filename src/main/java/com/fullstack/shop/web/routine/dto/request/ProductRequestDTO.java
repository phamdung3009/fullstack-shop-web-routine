package com.fullstack.shop.web.routine.dto.request;

import com.fullstack.shop.web.routine.entities.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDTO {
	String nameProduct;
	double price;
	String color;
	String title;
	Category category;
	Integer quantity;
	String description;
}
