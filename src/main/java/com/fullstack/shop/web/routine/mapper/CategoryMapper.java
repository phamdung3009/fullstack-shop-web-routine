package com.fullstack.shop.web.routine.mapper;

import com.fullstack.shop.web.routine.dto.response.CategoryResponseDTO;
import com.fullstack.shop.web.routine.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	CategoryResponseDTO categoryToCategoryResponseDTO(Category category);
}
