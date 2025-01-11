package com.fullstack.shop.web.routine.mapper;

import com.fullstack.shop.web.routine.dto.request.CustomerRequestDTO;
import com.fullstack.shop.web.routine.dto.response.CustomerReponseDTO;
import com.fullstack.shop.web.routine.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	CustomerReponseDTO toEntities(Customer customer);

	Customer toDto(CustomerRequestDTO requestDTO);

	void update(@MappingTarget Customer customer, CustomerRequestDTO requestDTO);
}
