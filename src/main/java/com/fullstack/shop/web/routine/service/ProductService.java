package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.entities.Product;
import com.fullstack.shop.web.routine.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
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
