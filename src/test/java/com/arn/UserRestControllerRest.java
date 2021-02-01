package com.arn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.arn.model.User;
import com.arn.service.IUserService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class UserRestControllerRest {

	@Autowired
	private  MockMvc mockMvc;
	
	@MockBean
	private IUserService service;
	
	@Test
	public void testSaveUser() throws Exception {	
	MvcResult result = mockMvc.perform(post("/user/register")
	.content("{ \"userName\":\"sivateja\",\"userEmail\":\"mallevarisivateja68@gmail.com\",\"userPassword\":\"teja\"}")
	.contentType(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse resp = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(),resp.getStatus());
		assertNotNull(resp.getContentAsString());	
	}
	
	@Test
	public void testGetUser() throws Exception {	
		User user = new User(1,"dj","sks@gmail.com","teja");
		User user2 = new User(2,"dj","sks@gmail.com","teja");
		
		when(service.getAllUser()).thenReturn(Arrays.asList(user,user2));
	MvcResult result = mockMvc.perform(get("/user/all"))
			.andReturn();
		MockHttpServletResponse resp = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(),resp.getStatus());
		assertNotNull(resp.getContentAsString());	
	}
}
