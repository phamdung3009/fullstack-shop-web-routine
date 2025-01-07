package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.dto.response.CategoryResponseDTO;
import com.fullstack.shop.web.routine.entities.Category;
import com.fullstack.shop.web.routine.mapper.CategoryMapper;
import com.fullstack.shop.web.routine.repository.CategoryRepository;
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
public class CategoryService {
	CategoryRepository categoryRepository;
	CategoryMapper categoryMapper;

	public Page<CategoryResponseDTO> getAllCategories(Pageable pageable) {
		try {
			Page<Category> categories = categoryRepository.findAll(pageable);
			List<CategoryResponseDTO> catDtos = new ArrayList<>();
			for (Category cat : categories) {
				CategoryResponseDTO dto = categoryMapper.categoryToCategoryResponseDTO(cat);
				catDtos.add(dto);
			}
			return new PageImpl<>(catDtos, pageable, categories.getTotalElements());
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
