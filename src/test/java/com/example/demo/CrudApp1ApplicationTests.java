package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.request.CreateUserRequest;
import com.example.demo.service.UserService;

//@RunWith(SpringRunner.class)
@SpringBootTest
class CrudApp1ApplicationTests {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserDao userDao;
	
	@Test
	public void getUsersTest() {
		when(userDao.findAll()).thenReturn(Stream
				.of(new User("Mohan" , 34 , "male" , "user" ,"mohan@test.com"),new User("Simran" , 24 , "female" , "user" , "simran@test.com")).collect(Collectors.toList()));
		assertEquals(2, userService.getUsers().getUserList().size());
	}
	
	@Test
	public void createUserTest() {
		CreateUserRequest reqObj = new CreateUserRequest();
		reqObj.setName("Mohan");
		reqObj.setAge(34);
		reqObj.setEmail("mohan@test.com");
		reqObj.setGender("male");
		reqObj.setType("user");
		
		String email = "mohan@test.com";
		
		User user = new User("Mohan" , 34 , "male" , "user" , "mohan@test.com");
		when(userDao.save(user)).thenReturn(user);
		when(userDao.findByEmail(email)).thenReturn(null);
		assertEquals(34, userService.createUser(reqObj).getUser().getAge());
	}
	
	@Test
	public void getUserByIdTest() {
		String id1 = "0";
		int id = 0;
		User user = new User("Mohan" , 34 , "male" , "user" , "mohan@test.com");
		when(userDao.findById(id)).thenReturn(Optional.ofNullable(user));
		assertEquals(id , userService.getUserById(id1).getUser().getId());
	}

}
