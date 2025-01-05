package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.dto.CategoryDTO;
import com.fullstack.shop.web.routine.entities.Category;
import com.fullstack.shop.web.routine.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;

	private final ModelMapper modelMapper;

	public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	public Page<CategoryDTO> getAllCategories(Pageable pageable) {
		try {
			Page<Category> categories = categoryRepository.findAll(pageable);
			List<CategoryDTO> categoryDTOList = new ArrayList<>();
			for (Category category : categories.getContent()) {
				CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
				categoryDTOList.add(categoryDTO);
			}
			return new PageImpl<>(categoryDTOList, pageable, categories.getTotalElements());
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
