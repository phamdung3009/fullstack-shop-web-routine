package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.dto.request.ProductRequestDTO;
import com.fullstack.shop.web.routine.dto.response.ProductResponseDTO;
import com.fullstack.shop.web.routine.entities.Product;
import com.fullstack.shop.web.routine.mapper.ProductMapper;
import com.fullstack.shop.web.routine.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {

	ProductRepository productRepository;
	ProductMapper productMapper;


	public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
		Page<Product> products = productRepository.findAll(pageable);
		List<ProductResponseDTO> proResponseDtos = new ArrayList<>();
		for (Product product : products.getContent()) {
			ProductResponseDTO proDto = productMapper.toEntities(product);
			proResponseDtos.add(proDto);
		}
		return new PageImpl<>(proResponseDtos, pageable, products.getTotalElements());
	}

	public Product getProductById(Integer id) {
		return productRepository.findById(id).orElse(null);
	}

	public Product saveProduct(ProductRequestDTO productRequestDTO) {
		Product product = productMapper.toDto(productRequestDTO);
		return productRepository.save(product);
	}

	public Product updateProduct(int id, ProductRequestDTO productRequestDTO) {
		Product product = productRepository.findById(id).orElse(null);
		productMapper.update(product, productRequestDTO);
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
