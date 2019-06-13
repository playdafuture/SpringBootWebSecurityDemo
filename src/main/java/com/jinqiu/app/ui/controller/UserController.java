package com.jinqiu.app.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jinqiu.app.exceptions.UserServiceException;
import com.jinqiu.app.service.UserService;
import com.jinqiu.app.shared.dto.UserDTO;
import com.jinqiu.app.ui.model.request.UserDetailsRequestModel;
import com.jinqiu.app.ui.model.response.ErrorMessages;
import com.jinqiu.app.ui.model.response.OperationStatusModel;
import com.jinqiu.app.ui.model.response.RequestOperationName;
import com.jinqiu.app.ui.model.response.RequestOperationStatus;
import com.jinqiu.app.ui.model.response.UserRest;



@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping(path="/{userId}",
				produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UserRest getUser(@PathVariable String userId) throws Exception {
		UserRest returnValue = new UserRest();
		UserDTO userDTO = userService.getUserByUserId(userId);
		BeanUtils.copyProperties(userDTO, returnValue);
		return returnValue;
	}

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
				 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
		//Error Check
		if (userDetails.getFirstName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		UserRest returnValue = new UserRest();
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(userDetails, dto);

		UserDTO createdUser = userService.createUser(dto);
		BeanUtils.copyProperties(createdUser, returnValue);
		return returnValue;
	}

	@PutMapping(path="/{userId}",
				consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			 	produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UserRest updateUser(@PathVariable String userId, 
							 @RequestBody UserDetailsRequestModel userDetails)
									 								throws Exception {		
		//Error Check
		if (userDetails.getFirstName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		UserRest returnValue = new UserRest();
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(userDetails, dto);

		UserDTO updatedUser = userService.updateUser(userId, dto);
		BeanUtils.copyProperties(updatedUser, returnValue);
		return returnValue;
	}

	@DeleteMapping(path="/{userId}",				   
				   produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public OperationStatusModel deleteUser(@PathVariable String userId) throws Exception {
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
		userService.deleteUser(userId);
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return operationStatusModel;
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<UserRest> getUsers(@RequestParam(value="page",  defaultValue = "0")  int page,
								   @RequestParam(value="limit", defaultValue = "25") int limit) throws Exception {
		List<UserRest> returnValue = new ArrayList<>();
		List<UserDTO> queryDTOs = userService.getUsers(page, limit);
		for (UserDTO u: queryDTOs) { //individually copy each user into the list
			UserRest tempRest = new UserRest();
			BeanUtils.copyProperties(u, tempRest);
			returnValue.add(tempRest);
		}
		return returnValue;
	}
}
