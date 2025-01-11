package com.fullstack.shop.web.routine.controller;

import com.fullstack.shop.web.routine.dto.request.CategoryRequestDTO;
import com.fullstack.shop.web.routine.dto.response.CategoryResponseDTO;
import com.fullstack.shop.web.routine.entities.Category;
import com.fullstack.shop.web.routine.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
	CategoryService categoryService;

	@GetMapping("/all")
	public ResponseEntity<Page<CategoryResponseDTO>> getAllCategories(Pageable pageable) {
		try {
			Page<CategoryResponseDTO> categories = categoryService.getAllCategories(pageable);
			if (categories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(categories, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Integer id) {
		CategoryResponseDTO result = categoryService.searchById(id);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Category> saveCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
		Category save = categoryService.saveCategory(categoryRequestDTO);
		if (save == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody CategoryRequestDTO categoryRequestDTO) {
		Category cate = categoryService.updateCategory(id, categoryRequestDTO);
		if (cate == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(cate, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteCategory(@PathVariable Integer id) {
		if (categoryService.delete(id)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.OK);
	}
}
