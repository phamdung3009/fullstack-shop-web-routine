package com.fullstack.shop.web.routine.dto.request;

import com.fullstack.shop.web.routine.entities.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionsRequestDTO {
	Double promotionType;
	Product product;
}
