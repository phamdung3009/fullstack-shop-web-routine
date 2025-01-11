package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.dto.request.CustomerRequestDTO;
import com.fullstack.shop.web.routine.dto.response.CustomerReponseDTO;
import com.fullstack.shop.web.routine.entities.Customer;
import com.fullstack.shop.web.routine.mapper.CustomerMapper;
import com.fullstack.shop.web.routine.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
	CustomerRepository customerRepository;
	CustomerMapper customerMapper;

	public Page<CustomerReponseDTO> getAlCustomers(Pageable pageable) {
		Page<Customer> customers = customerRepository.findAll(pageable);
		List<CustomerReponseDTO> cusResDtos = new ArrayList<>();
		for (Customer customer : customers.getContent()) {
			CustomerReponseDTO resDto = customerMapper.toEntities(customer);
			cusResDtos.add(resDto);
		}
		return new PageImpl<>(cusResDtos, pageable, customers.getTotalElements());
	}

	public Customer searchById(Integer id) {
		return customerRepository.findById(id).orElse(null);
	}

	public Customer saveCustomer(CustomerRequestDTO requestDTO) {
		Customer customer = customerMapper.toDto(requestDTO);
		return customerRepository.save(customer);
	}

	public Customer updateCustomer(Integer id, CustomerRequestDTO requestDTO) {
		Customer customer = customerRepository.findById(id).orElse(null);
		customerMapper.update(customer, requestDTO);
		return customerRepository.save(customer);
	}

	public Boolean delete(Integer id) {
		boolean checkResult = customerRepository.existsById(id);
		if (checkResult) {
			customerRepository.deleteById(id);
		}
		return checkResult;
	}
}
