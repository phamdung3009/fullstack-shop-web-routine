package com.fullstack.shop.web.routine.mapper;

import com.fullstack.shop.web.routine.dto.request.CategoryRequestDTO;
import com.fullstack.shop.web.routine.dto.response.CategoryResponseDTO;
import com.fullstack.shop.web.routine.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	CategoryResponseDTO CategoryResponseDTOToCategory(Category category);

	Category categoryToCategoryResponseDTO(CategoryResponseDTO categoryResponseDTO);

	Category categoryToCategoryRequestDTO(CategoryRequestDTO categoryRequestDTO);

	void updateCategory(@MappingTarget Category category, CategoryRequestDTO categoryRequestDTO);
}
