package com.fullstack.shop.web.routine.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDTO {
	String firstName;
	String lastName;
	String email;
	String address;
	String password;
	String confirmPassword;
}
