package com.apress.ravi.UserRegistrationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apress.ravi.UserRegistrationSystem.dto.UserInfo;

@Repository
public interface UserInfoJpaRepository extends JpaRepository<UserInfo, Long> {
	public UserInfo findByUsername(String username);
}
