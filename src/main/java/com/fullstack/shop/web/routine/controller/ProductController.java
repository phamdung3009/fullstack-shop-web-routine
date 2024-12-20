package com.fullstack.shop.web.routine.controller;

import com.fullstack.shop.web.routine.entities.Product;
import com.fullstack.shop.web.routine.entities.Promotions;
import com.fullstack.shop.web.routine.service.ProductService;
import com.fullstack.shop.web.routine.service.PromotionsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Product>> getProductList(Pageable pageable) {
        Page<Product> productPage = productService.getAllProducts(pageable);
        if (productPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        Product checkProductId = productService.getProductById(id);
        if (checkProductId == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(checkProductId, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product checkCreated = productService.saveOrUpdateProduct(product);
        if (checkCreated == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(checkCreated, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
        product.setId(id);
        Product checkUpdated = productService.saveOrUpdateProduct(product);
        if (checkUpdated == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(checkUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") Integer id) {
        if (productService.deleteProductById(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    @GetMapping("/searchName")
    public ResponseEntity<Page<Product>> searchProductByName(@RequestParam String name, @RequestParam int page, @RequestParam int size) {
        Page<Product> productsByName = productService.searchProductsByName(name, page, size);
        if (productsByName.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productsByName, HttpStatus.OK);
    }

    @GetMapping("/searchId")
    public ResponseEntity<Page<Product>> searchProductByCatId(@RequestParam int catId, @RequestParam int page, @RequestParam int size) {
        Page<Product> productsById = productService.searchProductsByCatId(catId, page, size);
        if (productsById.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productsById, HttpStatus.OK);
    }
}
