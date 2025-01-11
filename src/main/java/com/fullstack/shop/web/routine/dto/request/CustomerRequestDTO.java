package com.fullstack.shop.web.routine.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequestDTO {
	String fullName;
	Integer phoneNumber;
	String address;
}
