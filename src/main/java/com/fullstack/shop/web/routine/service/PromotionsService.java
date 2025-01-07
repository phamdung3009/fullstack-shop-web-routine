package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.dto.response.PromotionsResponseDTO;
import com.fullstack.shop.web.routine.entities.Promotions;
import com.fullstack.shop.web.routine.mapper.PromotionsMapper;
import com.fullstack.shop.web.routine.repository.PromotionsRepository;
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
public class PromotionsService {

	PromotionsRepository promotionsRepository;
	PromotionsMapper promotionsMapper;

	public Page<PromotionsResponseDTO> getPromotions(Pageable pageable) {
		Page<Promotions> promotionsPage = promotionsRepository.findAll(pageable);
		List<PromotionsResponseDTO> dtos = new ArrayList<>();
		for (Promotions promotions : promotionsPage.getContent()) {
			PromotionsResponseDTO dto = promotionsMapper.entityToDto(promotions);
			dtos.add(dto);
		}
		return new PageImpl<>(dtos, pageable, promotionsPage.getTotalElements());
	}

	public Promotions getPromotionsById(Integer id) {
		return promotionsRepository.findById(id).orElse(null);
	}

	public Promotions saveOrUpdatePromotions(Promotions promotions) {
		return promotionsRepository.save(promotions);
	}

	public Boolean deletePromotionsById(Integer id) {
		boolean checkExists = promotionsRepository.existsById(id);
		if (checkExists) {
			promotionsRepository.deleteById(id);
		}
		return checkExists;
	}
}
