package com.vizha.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vizha.dto.ResponseDTO;
import com.vizha.dto.UserDTO;
import com.vizha.dto.UserListDTO;
import com.vizha.model.UserModel;
import com.vizha.repository.UserRepository;
import com.vizha.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String save(UserDTO userDetails) {

		String response = "";

		try {
			UserModel userModel = UserModel.builder().username(userDetails.getUserName()).email(userDetails.getEmail())
					.phone(userDetails.getPhone()).firstname(userDetails.getFirstName())
					.lastname(userDetails.getLastName()).active(true).createdtime(LocalDateTime.now()).build();

			if (!userDetails.getPassword().isEmpty()) {
				userModel.setPassword(passwordEncoder.encode(userDetails.getPassword()));
			}

			userRepository.save(userModel);

			response = "Succes";
		} catch (Exception ex) {
			response = "Error :: " + ex.getLocalizedMessage();
		}
		return response;
	}

	public UserListDTO list(UserListDTO userListDTO) {
		
		Pageable paging = null;
		
		if(userListDTO.getPageNumber() > 0 && userListDTO.getListSize() > 0) {
			paging = PageRequest.of(userListDTO.getPageNumber() - 1, userListDTO.getListSize());
		} else {
			paging = PageRequest.of(0, 25);
		}
		
		Specification<UserModel> spec = Specification.where(null);
		
//		spec.and(UserSpecifications)

		return userListDTO;
	}

	private UserDTO constructUserDeatils(UserModel userModel) {

		UserDTO userDTO = UserDTO.builder().id(userModel.getId()).userName(userModel.getUsername())
				.email(userModel.getEmail()).phone(userModel.getPhone()).firstName(userModel.getFirstname())
				.lastName(userModel.getLastname()).build();

		return userDTO;
	}

	public UserDTO get(long id) {

		ResponseDTO response = new ResponseDTO();
		UserDTO userDTO = new UserDTO();

		try {
			if (id == 0) {
				throw new Exception("Invalid Id");
			}

			UserModel userModel = userRepository.findByIdAndActive(id, false);

			userDTO = constructUserDeatils(userModel);

			response.setResponseStatus("Success");
		} catch (Exception ex) {
			response.setResponseStatus("Failed");
			response.setResponseMessage("Reason :: " + ex.getMessage());
		}

		userDTO.setResponse(response);

		return userDTO;
	}
}