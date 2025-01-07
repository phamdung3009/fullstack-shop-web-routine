package com.fullstack.shop.web.routine.mapper;

import com.fullstack.shop.web.routine.dto.response.CustomerReponseDTO;
import com.fullstack.shop.web.routine.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	CustomerReponseDTO customerToCustomerReponseDTO(Customer customer);
}
