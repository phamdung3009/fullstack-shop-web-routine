package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.dto.request.UserRequestDTO;
import com.fullstack.shop.web.routine.dto.response.UserResponseDTO;
import com.fullstack.shop.web.routine.entities.User;
import com.fullstack.shop.web.routine.mapper.UserMapper;
import com.fullstack.shop.web.routine.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
	UserRepository userRepository;
	UserMapper userMapper;

	public Boolean checkExistLogin(String email, String password) {
		User user = userRepository.checkExistEmailPassword(email, password);
		if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
		Page<User> users = userRepository.findAll(pageable);
		List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
		for (User user : users.getContent()) {
			UserResponseDTO userResponseDTO = userMapper.userResponseDtoToUserEntities(user);
			userResponseDTOS.add(userResponseDTO);
		}
		return new PageImpl<>(userResponseDTOS, pageable, users.getTotalElements());
	}

	public UserResponseDTO getUserById(int id) {
		User user = userRepository.findById(id).orElse(null);
		UserResponseDTO userResponseDTO = userMapper.userResponseDtoToUserEntities(user);
		return userResponseDTO;
	}
	
	public User updateUser(int id, UserRequestDTO userRequestDTO) {
		// Cach 1 pho thong
		/*User user = userRepository.findById(id).orElse(null);
		user.setFirstName(userRequestDTO.getFirstName());
		user.setLastName(userRequestDTO.getLastName());
		user.setEmail(userRequestDTO.getEmail());
		user.setAddress(userRequestDTO.getAddress());
		user.setPassword(userRequestDTO.getPassword());
		user.setConfirmPassword(userRequestDTO.getConfirmPassword());
		user = userRepository.save(user);
		return user;*/

		// Cach 2 su dụng mapstruct
		User user = userRepository.findById(id).orElse(null);
		userMapper.updateUser(user, userRequestDTO);
		return userRepository.save(user);
	}

	public Boolean deleteUser(int id) {
		boolean checkExists = userRepository.existsById(id);
		if (checkExists) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public User checkOrCreateUser(String email, UserRequestDTO userRequestDTO) {
		User checkExists = userRepository.checkExistEmail(email);
		if (checkExists != null) {
			return checkExists;
		} else {
			User user = userMapper.userEntitiesToUserRequestDto(userRequestDTO);
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
			user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
			return userRepository.save(user);
		}
	}
}
