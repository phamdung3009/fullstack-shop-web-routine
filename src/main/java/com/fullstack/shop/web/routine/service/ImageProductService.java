package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.entities.ImageProduct;
import com.fullstack.shop.web.routine.repository.ImageProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImageProductService {

    private final ImageProductRepository imageProductRepository;

    public ImageProductService(ImageProductRepository imageProductRepository) {
        this.imageProductRepository = imageProductRepository;
    }

    public Page<ImageProduct> getAllImageProducts(Pageable pageable) {
        return imageProductRepository.findAll(pageable);
    }

    public ImageProduct getImageProductById(Integer id) {
        return imageProductRepository.findById(id).orElse(null);
    }

    public ImageProduct saveOrUpdateImageProduct(ImageProduct imageProduct) {
        return imageProductRepository.save(imageProduct);
    }

    public Boolean deleteImageProduct(Integer id) {
        boolean checkExist = imageProductRepository.existsById(id);
        if (checkExist) {
            imageProductRepository.deleteById(id);
        }
        return checkExist;
    }
}
