package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.entities.Category;
import com.fullstack.shop.web.routine.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<Category> getAllCategories(Pageable pageable) {
        try {
            return categoryRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Category searchById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category saveOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    public Boolean delete(Integer id) {
        boolean checkExist = categoryRepository.existsById(id);
        if (checkExist) {
            categoryRepository.deleteById(id);
        }
        return checkExist;
    }
}
