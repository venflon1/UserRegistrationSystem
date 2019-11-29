package com.apress.ravi.UserRegistrationSystem.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apress.ravi.UserRegistrationSystem.dto.UserDTO;
import com.apress.ravi.UserRegistrationSystem.repository.UserJpaRepository;

@RestController
@RequestMapping("api/user")
public class UserRegistrationRestController {

	// that is used for writing log 
	public static final Logger logger = LoggerFactory.getLogger(UserRegistrationRestController.class);
	 
	@Autowired
	UserJpaRepository userJpaRepository;
	
	//@RequestMapping(value="/" method=RequestMethod.GET)
	@GetMapping(value="/")
	public ResponseEntity<List<UserDTO>> listAllUser(){
		
		// retrieve all user by db
		List<UserDTO> body = this.userJpaRepository.findAll();
		this.logger.info("Retrive all user");
		
		ResponseEntity<List<UserDTO>> response = new ResponseEntity<List<UserDTO>>(body, HttpStatus.OK);
		return response;
	}
	
	//@RequestMapping(value="/" method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value="/",  consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> createUser(@RequestBody final UserDTO user){
		
		// save user that pass on request entire object
		UserDTO body = this.userJpaRepository.save(user);
		this.logger.info("save user");
		
		ResponseEntity<UserDTO> response = new ResponseEntity<UserDTO>(body, HttpStatus.CREATED);
		return response;
	}
	
	//@RequestMapping(value="/{id}" method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value="/{id}",  consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> retrieveUserById(@PathVariable final long id){
		// retrieve user by db
		UserDTO body = this.userJpaRepository.findById(id);
		this.logger.info("Retrive user");

		ResponseEntity<UserDTO> response = new ResponseEntity<UserDTO>(body, HttpStatus.OK);
		return response;
	}
	
	//@RequestMapping(value="/{id}" method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(value="/{id}",  consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> updtaeUserById(@PathVariable final long id, @RequestBody UserDTO userUpdate){
		
		// retrieve user by db
		UserDTO userExctractRequest = this.userJpaRepository.findById(id);
		this.logger.info("Retrive user");

		// update existing user with new details that pass on request with an object
		userExctractRequest.setName(userUpdate.getName());
		userExctractRequest.setAddress(userUpdate.getAddress());
		userExctractRequest.setEmail(userUpdate.getEmail());
		
		// store user with changed details
		this.userJpaRepository.saveAndFlush(userExctractRequest);
		this.logger.info("user updated");

		ResponseEntity<UserDTO> response = new ResponseEntity<UserDTO>(userExctractRequest, HttpStatus.OK);
		return response;
	}
	
	//@RequestMapping(value="/{id}" method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value="/{id}",  consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> deleteUserById(@PathVariable final long id){
		
		// retrieve user by db
		UserDTO userExctractRequest = this.userJpaRepository.findById(id);
		this.logger.info("Retrive user");

		// delete user on db
		this.userJpaRepository.delete(userExctractRequest);
		this.logger.info("user deleted");

		ResponseEntity<UserDTO> response = new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
		return response;
	}
}
