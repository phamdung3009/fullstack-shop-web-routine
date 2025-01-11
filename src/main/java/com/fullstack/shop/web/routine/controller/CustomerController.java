package com.fullstack.shop.web.routine.controller;

import com.fullstack.shop.web.routine.dto.request.CustomerRequestDTO;
import com.fullstack.shop.web.routine.dto.response.CustomerReponseDTO;
import com.fullstack.shop.web.routine.entities.Customer;
import com.fullstack.shop.web.routine.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/all")
	public ResponseEntity<Page<CustomerReponseDTO>> getAllCustomers(Pageable pageable) {
		Page<CustomerReponseDTO> customerPage = customerService.getAlCustomers(pageable);
		if (customerPage.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(customerPage, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(Integer id) {
		Customer customer = customerService.searchById(id);
		if (customer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Customer> createCustomer(CustomerRequestDTO requestDTO) {
		Customer createCus = customerService.saveCustomer(requestDTO);
		if (createCus == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(createCus, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, CustomerRequestDTO requestDTO) {
		Customer updateCus = customerService.updateCustomer(id, requestDTO);
		if (updateCus == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updateCus, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteCustomer(@PathVariable Integer id) {
		if (customerService.delete(id)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.OK);
	}
}
