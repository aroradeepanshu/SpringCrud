package com.example.demo.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.request.CreateUserRequest;
import com.example.demo.response.CreateUserResponse;
import com.example.demo.response.GetAllUserResponse;
import com.example.demo.response.GetUserByIdResponse;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
	
	Properties messageProp = new Properties();
	{try{
//		InputStream input = new FileInputStream("src/main/resources/message.properties");
//		messageProp.load(input);
		
		InputStream in = getClass().getResourceAsStream("../../../../message.properties");
		messageProp.load(in);
		
	}catch(Exception e){
		log.error(e.getMessage() + "\n");
	}}
	
	

	@Autowired
	GetUserByIdResponse getUserByIdResponse;
	
	@Autowired
	CreateUserResponse createUserResponse;
	
	@Override
	public GetAllUserResponse getUsers() {
		log.info("Entered getUsers function");
		GetAllUserResponse getAllUserResponse = new GetAllUserResponse(); 
		try {
			List<User> userList = userDao.findAll();
			if(userList.isEmpty()) {
				getAllUserResponse.setMessage("List is empty");
				getAllUserResponse.setStatus(200);
				getAllUserResponse.setUserList(userList);
			}else {
				getAllUserResponse.setStatus(200);
				getAllUserResponse.setMessage(messageProp.getProperty("success_msg"));
				getAllUserResponse.setUserList(userList);
			}
		}catch(Exception e) {
			log.error(e.getMessage() + "\n");
			getAllUserResponse.setStatus(500);
			getAllUserResponse.setMessage(messageProp.getProperty("server_error"));
			getAllUserResponse.setUserList(null);
		}
		log.info("returning getAllUserResponse");
		return getAllUserResponse;
	}



	@Override
	public GetUserByIdResponse getUserById(String id) {
		log.info("entered getUserByID");
		int parsedId;
		
		try {
			log.info("parsing id");
			parsedId = Integer.parseInt(id);
		}catch(Exception e) {
			log.error(e.getMessage());
			getUserByIdResponse.setStatus(400);
			getUserByIdResponse.setMessage(messageProp.getProperty("invalid_id_msg"));
			getUserByIdResponse.setUser(null);
			log.error("Unable to parse returning null user");
			return getUserByIdResponse;
		}
		try {
			Optional<User> user  = userDao.findById(parsedId);
			User userObj;
			if(user.isPresent()) {
			 userObj = user.get();
			}else {
			 userObj=null;
			}
			
			
			if(userObj != null) {
				getUserByIdResponse.setStatus(200);
				getUserByIdResponse.setMessage(messageProp.getProperty("success_msg"));
				getUserByIdResponse.setUser(userObj);
			}else {
				getUserByIdResponse.setStatus(404);
				getUserByIdResponse.setMessage(messageProp.getProperty("user_not_found_msg"));
				getUserByIdResponse.setUser(null);
			}
			}catch(Exception e) {
				log.error(e.getMessage() + "\n");
				getUserByIdResponse.setStatus(500);
				getUserByIdResponse.setMessage(messageProp.getProperty("server_error"));
				getUserByIdResponse.setUser(null);
			}
		
		
		log.info("returning getUserByIdResponse and exiting function");
		return getUserByIdResponse;
	}

	@Override
	public CreateUserResponse createUser(CreateUserRequest reqObj) {
		log.info("entered CreateUserResponse");
		try {
		
		String name = reqObj.getName();
		int age = reqObj.getAge();
		String gender = reqObj.getGender().toLowerCase();
		String type = reqObj.getType().toLowerCase();
		String email = reqObj.getEmail().toLowerCase();
		
		if(!name.matches("[a-zA-Z]+")){
			createUserResponse.setMessage(messageProp.getProperty("invalid_name_msg"));
			createUserResponse.setStatus(400);
			createUserResponse.setUser(null);
			return createUserResponse;
		}
		
		if(age<18 || age>60) {
			createUserResponse.setMessage("Age should be between 18 and 60");
			createUserResponse.setStatus(400);
			createUserResponse.setUser(null);
			return createUserResponse;
		}
			
		if(!(gender.equals("male") || gender.equals("female"))) {
			createUserResponse.setMessage(messageProp.getProperty("invalid_gender_msg"));
			createUserResponse.setStatus(400);
			createUserResponse.setUser(null);
			return createUserResponse;
		}
		
		if(!(type.equals("user") || type.equals("admin"))) {
			createUserResponse.setMessage(messageProp.getProperty("invalid_type_msg"));
			createUserResponse.setStatus(400);
			createUserResponse.setUser(null);
			return createUserResponse;
		}
		
		if(!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			createUserResponse.setMessage(messageProp.getProperty("invalid_email_msg"));
			createUserResponse.setStatus(400);
			createUserResponse.setUser(null);
			return createUserResponse;
		}
		
		if(userDao.findByEmail(email) != null) {
			createUserResponse.setMessage(messageProp.getProperty("email_exist_msg"));
			createUserResponse.setStatus(400);
			createUserResponse.setUser(null);
			return createUserResponse;
		}

		User user = new User(name,age, gender ,type,email);
		
		userDao.save(user);
		createUserResponse.setMessage(messageProp.getProperty("success_msg"));
		createUserResponse.setStatus(201);
		createUserResponse.setUser(user);
		}catch(Exception e) {
			log.error(e.getMessage() + "\n");
			createUserResponse.setMessage(messageProp.getProperty("server_error"));
			createUserResponse.setStatus(500);
			createUserResponse.setUser(null);
		}
		log.info("returning from CreateUser");
		return createUserResponse;
	}

}
