package com.example.demo.service;

import com.example.demo.request.CreateUserRequest;
import com.example.demo.response.CreateUserResponse;
import com.example.demo.response.GetAllUserResponse;
import com.example.demo.response.GetUserByIdResponse;

public interface UserService {
	
	public GetAllUserResponse getUsers();
	
	public GetUserByIdResponse getUserById(String id);
	
	public CreateUserResponse createUser(CreateUserRequest reqObj);

}
