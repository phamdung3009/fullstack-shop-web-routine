package com.fullstack.shop.web.routine.dto.response;

import com.fullstack.shop.web.routine.entities.Customer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrdersResponseDTO {
	Integer id;
	Integer amount;
	LocalDateTime orderDate;
	Boolean status;
	Customer customer;
}
