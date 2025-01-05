package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.dto.response.ProductResponseDto;
import com.fullstack.shop.web.routine.entities.Product;
import com.fullstack.shop.web.routine.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;

	public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}

	public Page<ProductResponseDto> getAllProducts(Pageable pageable) {
		try {
			Page<Product> products = productRepository.findAll(pageable);
			List<ProductResponseDto> responseDtos = new ArrayList<>();
			for (Product product : products.getContent()) {
				ProductResponseDto productResponseDto = modelMapper.map(product, ProductResponseDto.class);
				responseDtos.add(productResponseDto);
			}
			return new PageImpl<>(responseDtos, pageable, products.getTotalElements());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Product getProductById(Integer id) {
		return productRepository.findById(id).orElse(null);
	}

	public Product saveOrUpdateProduct(Product product) {
		return productRepository.save(product);
	}

	public Boolean deleteProductById(Integer id) {
		boolean checkExist = productRepository.existsById(id);
		if (checkExist) {
			productRepository.deleteById(id);
		}
		return checkExist;
	}

	public Page<Product> searchProductsByName(String name, int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		return productRepository.searchByName(name, pageable);
	}

	public Page<Product> searchProductsByCatId(int catId, int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
		return productRepository.searchByCatId(catId, pageable);
	}
}
