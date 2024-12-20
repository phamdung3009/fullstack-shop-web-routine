package com.fullstack.shop.web.routine.controller;

import com.fullstack.shop.web.routine.entities.ImageProduct;
import com.fullstack.shop.web.routine.service.ImageProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imageProduct")
public class ImageProductController {
    private final ImageProductService imageProductService;

    public ImageProductController(ImageProductService imageProductService) {
        this.imageProductService = imageProductService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ImageProduct>> getImageProductList(Pageable pageable) {
        Page<ImageProduct> getAllImageProducts = imageProductService.getAllImageProducts(pageable);
        if (getAllImageProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(getAllImageProducts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageProduct> getImageProduct(@PathVariable("id") Integer id) {
        ImageProduct checkId = imageProductService.getImageProductById(id);
        if (checkId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(checkId, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ImageProduct> createImageProduct(@RequestBody ImageProduct imageProduct) {
        ImageProduct checkCreated = imageProductService.saveOrUpdateImageProduct(imageProduct);
        if (checkCreated == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(checkCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageProduct> updateImageProduct(@PathVariable("id") Integer id, @RequestBody ImageProduct imageProduct) {
        imageProduct.setId(id);
        ImageProduct checkUpdated = imageProductService.saveOrUpdateImageProduct(imageProduct);
        if (checkUpdated == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(checkUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteImageProduct(@PathVariable("id") Integer id) {
        if (imageProductService.deleteImageProduct(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
