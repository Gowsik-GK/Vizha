package com.vizha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vizha.dto.UserDTO;
import com.vizha.service.UserService;

import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping("/user/")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "save", produces = { MediaType.APPLICATION_JSON })
	public String save(@RequestBody UserDTO userDetails) {
		String response = userService.save(userDetails);
		return response;
	}
}
