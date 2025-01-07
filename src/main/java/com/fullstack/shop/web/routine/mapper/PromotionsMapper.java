package com.fullstack.shop.web.routine.mapper;

import com.fullstack.shop.web.routine.dto.response.PromotionsResponseDTO;
import com.fullstack.shop.web.routine.entities.Promotions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromotionsMapper {
	PromotionsResponseDTO entityToDto(Promotions promotions);
}
