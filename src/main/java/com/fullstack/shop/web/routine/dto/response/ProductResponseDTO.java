package com.fullstack.shop.web.routine.dto.response;

import com.fullstack.shop.web.routine.entities.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDTO {
	Integer id;
	String nameProduct;
	double price;
	String color;
	String title;
	Category category;
	Integer quantity;
	String description;
}
