package com.fullstack.shop.web.routine.dto.response;

import com.fullstack.shop.web.routine.entities.Category;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductResponseDTO {
	private Integer idProduct;
	private String nameProductDto;
	private double priceProduct;
	private String colorProduct;
	private String titleProduct;
	private Category categoryProduct;
	private Integer quantityProduct;
	private String descriptionProduct;
	private LocalDateTime createdAtProduct;
	private LocalDateTime updatedAtProduct;
}
