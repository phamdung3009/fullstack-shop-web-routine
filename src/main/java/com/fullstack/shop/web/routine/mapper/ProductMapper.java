package com.fullstack.shop.web.routine.mapper;

import com.fullstack.shop.web.routine.dto.request.ProductRequestDTO;
import com.fullstack.shop.web.routine.dto.response.ProductResponseDTO;
import com.fullstack.shop.web.routine.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	ProductResponseDTO toEntities(Product product);

	Product toDto(ProductRequestDTO requestDTO);

	void update(@MappingTarget Product product, ProductRequestDTO requestDTO);
}
