package com.fullstack.shop.web.routine.dto.response;

import com.fullstack.shop.web.routine.entities.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionsResponseDTO {
	Integer id;
	Double promotionType;
	Product product;
	LocalDateTime endDate;
	LocalDateTime startDate;
}
