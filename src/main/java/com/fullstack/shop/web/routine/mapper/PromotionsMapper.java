package com.fullstack.shop.web.routine.mapper;

import com.fullstack.shop.web.routine.dto.request.PromotionsRequestDTO;
import com.fullstack.shop.web.routine.dto.response.PromotionsResponseDTO;
import com.fullstack.shop.web.routine.entities.Promotions;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PromotionsMapper {
	PromotionsResponseDTO toEntities(Promotions promotions);

	Promotions toDto(PromotionsRequestDTO requestDTO);

	void updatePromotions(PromotionsRequestDTO requestDTO, @MappingTarget Promotions promotions);
}
