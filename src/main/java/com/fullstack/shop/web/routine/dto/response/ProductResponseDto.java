package com.fullstack.shop.web.routine.dto.response;

import com.fullstack.shop.web.routine.entities.Category;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
	private Integer idDto;
	private String nameProductDto;
	private double priceDto;
	private String colorDto;
	private String titleDto;
	private Category categoryDto;
	private Integer quantityDto;
	private String descriptionDto;
}
