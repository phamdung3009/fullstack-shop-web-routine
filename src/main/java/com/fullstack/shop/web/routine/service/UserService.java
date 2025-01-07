package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.entities.User;
import com.fullstack.shop.web.routine.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
	UserRepository userRepository;

	public Boolean checkExist(String email, String password) {
		User user = userRepository.checkExistEmailPassword(email, password);
		if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
}
