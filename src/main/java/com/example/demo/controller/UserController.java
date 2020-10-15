package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.CreateUserRequest;
import com.example.demo.response.CreateUserResponse;
import com.example.demo.response.GetAllUserResponse;
import com.example.demo.response.GetUserByIdResponse;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "User-Resource-Controller-1")
public class UserController {
	
	@Autowired
	UserService userService;
	
    @GetMapping("v1/users")
    @ApiOperation(value="returns list of all users present in Database")
	public GetAllUserResponse getUsers() {
		return userService.getUsers();
	}
	
    @GetMapping("v1/user/{id}")
    @ApiOperation(value = "returns User with provided id if present")
    @ApiParam(value = "Id should be numeric integer type")
    public GetUserByIdResponse getUserById(@PathVariable("id") String id) {
    	return userService.getUserById(id);
    }
    
    @PostMapping("v1/user/create")
    @ApiOperation(value = "creates new user")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest reqObj) {
    	return userService.createUser(reqObj);
    }
}
