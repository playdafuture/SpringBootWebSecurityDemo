package com.jinqiu.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jinqiu.app.shared.dto.UserDTO;

public interface UserService extends UserDetailsService {
	UserDTO createUser(UserDTO user);
	UserDTO getUserByEmail(String email);
	UserDTO getUserByUserId(String userId);
}
