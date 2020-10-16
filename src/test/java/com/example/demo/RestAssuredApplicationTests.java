//package com.example.demo;
//
//import static io.restassured.RestAssured.given;
//
//import org.testng.annotations.Test;
//
//import com.example.demo.request.CreateUserRequest;
//
//import io.restassured.RestAssured;
//
//public class RestAssuredApplicationTests {
//	
////	@Value("${base_url}")
//	private String baseURL = "http://localhost:8080/";
//
//	@Test
//	public void getUsersAutomation() {
//		
//		System.out.println(baseURL);
//		
//		RestAssured.baseURI = baseURL;
//		String url = "/v1/users";
//		
//		given()
//		.when()
//		.get(url)
//		.then()
//		.assertThat()
//		.statusCode(200);
//	}
//	
//	@Test
//	public void getUserByIdAutomation() {
//        
//		RestAssured.baseURI = baseURL;
//		String url = "/v1/user/5";
//		
//		given()
//		.when()
//		.get(url)
//		.then()
//		.assertThat()
//		.statusCode(200);
//	}
//	
//	@Test
//	public void createUserAutomation() {
//        
//		RestAssured.baseURI = baseURL;
//		String url = "/v1/user/create";
//         
//        CreateUserRequest reqObj = new CreateUserRequest();
// 		reqObj.setName("Mohan");
// 		reqObj.setAge(34);
// 		reqObj.setEmail("mohan@test.com");
// 		reqObj.setGender("male");
// 		reqObj.setType("user");
//		
//		given()
//		.contentType("application/json")
//		.body(reqObj)
//		.when()
//		.post(url)
//		.then()
//		.assertThat()
//		.statusCode(200);
//	}
//	
//}
