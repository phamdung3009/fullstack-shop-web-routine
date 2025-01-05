package com.fullstack.shop.web.routine.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CategoryDTO {
	private Integer id;
	private String name;
	private String description;
}
