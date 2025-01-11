package com.fullstack.shop.web.routine.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDTO {
	Integer id;
	String firstName;
	String lastName;
	String email;
	String address;
	String password;
	String confirmPassword;
}
