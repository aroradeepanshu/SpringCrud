package com.example.demo.response;

import org.springframework.stereotype.Component;

import com.example.demo.entity.User;

@Component
public class GetUserByIdResponse extends BaseResponse{

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
