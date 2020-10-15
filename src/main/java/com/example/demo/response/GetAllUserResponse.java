package com.example.demo.response;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.entity.User;


@Component
public class GetAllUserResponse extends BaseResponse {

	private List<User> userList;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
