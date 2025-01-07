package com.fullstack.shop.web.routine.controller;

import com.fullstack.shop.web.routine.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
	UserService userService;

	@GetMapping("/login")
	public ResponseEntity<Boolean> checkExistEmailPassword(@RequestParam String email, @RequestParam String password) {
		try {
			boolean result = userService.checkExist(email, password);
			if (result) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
