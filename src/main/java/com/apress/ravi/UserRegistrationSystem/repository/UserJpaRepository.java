package com.apress.ravi.UserRegistrationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apress.ravi.UserRegistrationSystem.dto.UserDTO;

public interface UserJpaRepository extends JpaRepository<UserDTO, Integer> {
	
	public UserDTO findById(long id);
	
	public UserDTO findByName(String name);
	
	public UserDTO findByEmail(String email);
}
