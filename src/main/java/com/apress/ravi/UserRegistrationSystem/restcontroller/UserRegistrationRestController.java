package com.apress.ravi.UserRegistrationSystem.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apress.ravi.UserRegistrationSystem.dto.UserDTO;
import com.apress.ravi.UserRegistrationSystem.repository.UserJpaRepository;

@RestController
@RequestMapping("api/user")
public class UserRegistrationRestController {

	public static final Logger logger = LoggerFactory.getLogger(UserRegistrationRestController.class);
	 
	@Autowired
	UserJpaRepository UserJpaRepository;
	
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> listAllUser(){
		
		List<UserDTO> body = this.UserJpaRepository.findAll();
		ResponseEntity<List<UserDTO>> response = new ResponseEntity<List<UserDTO>>(body, HttpStatus.OK);
		return response;
	}
}
