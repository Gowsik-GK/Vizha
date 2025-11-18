package com.vizha.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class UserDTO {

	private long id;
	private String userName;
	private String password;
	private String email;
	private String phone;
	private String firstName;
	private String lastName;
	
	private ResponseDTO response;
}