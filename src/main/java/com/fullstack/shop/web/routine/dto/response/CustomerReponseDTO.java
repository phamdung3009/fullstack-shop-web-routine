package com.fullstack.shop.web.routine.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerReponseDTO {
	Integer id;
	String fullName;
	Integer phoneNumber;
	String address;
}
