package com.fullstack.shop.web.routine.controller;

import com.fullstack.shop.web.routine.dto.request.UserRequestDTO;
import com.fullstack.shop.web.routine.dto.response.UserResponseDTO;
import com.fullstack.shop.web.routine.entities.User;
import com.fullstack.shop.web.routine.repository.UserRepository;
import com.fullstack.shop.web.routine.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
	UserService userService;
	private final UserRepository userRepository;

	@GetMapping("/login")
	public ResponseEntity<Boolean> checkExistEmailPassword(@RequestParam String email, @RequestParam String password) {
		try {
			boolean result = userService.checkExistLogin(email, password);
			if (result) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<Page<UserResponseDTO>> getAllUsers(Pageable pageable) {
		Page<UserResponseDTO> result = userService.getAllUsers(pageable);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> getUser(@PathVariable Integer id) {
		UserResponseDTO result = userService.getUserById(id);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<User> created(@RequestBody UserRequestDTO userRequestDTO) {
		User user = userService.createUser(userRequestDTO);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody UserRequestDTO userRequestDTO) {
		User user = userService.updateUser(id, userRequestDTO);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
		boolean result = userService.deleteUser(id);
		if (result) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/register")
	public ResponseEntity<User> getUser(@RequestBody UserRequestDTO requestDTO) {
		User user = userService.checkOrCreateUser(requestDTO.getEmail(), requestDTO);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
