package com.vizha.user.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vizha.dto.UserDTO;
import com.vizha.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	UserService userService;
	
	@Test
	void userServiceTest () {
		
		UserDTO user = new UserDTO();
		
		userService.save(user);
		
		System.out.println("User Save Case is Success");
	}
}
