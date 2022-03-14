package com.example.demo.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.entity.Emp;

// boots the context
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // creates the MockMVC object
@ActiveProfiles("test") // sets current profile to 'test'
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = { "classpath:emp-schema.sql",
		"classpath:emp-data.sql" })
public class EmpControllerIntegrationTest {

	@Autowired // tells Spring to insert this object into the class
	private MockMvc mvc; // object for running fake requests

	@Autowired
	private ObjectMapper mapper; // the object Spring uses to convert JSON <-> Java

	@Test
	public void test() {
		assertEquals(2, 1 + 1);
	}

	@Test
	public void testCreate() throws Exception {
		// URL body method headers
		Emp testEmp = new Emp(19, "Donald", "Disneyworld", "Male");
		String testEmpAsJSON = this.mapper.writeValueAsString(testEmp);
		RequestBuilder req = post("/emp/create").content(testEmpAsJSON).contentType(MediaType.APPLICATION_JSON);

		Emp testSavedEmp = new Emp(2, 19, "Donald", "Disneyworld", "Male");
		String testSavedEmpAsJSON = this.mapper.writeValueAsString(testSavedEmp);
		// this will check the status code of my response
		ResultMatcher checkStatus = status().isCreated();
		// this will check the body of the response
		ResultMatcher checkBody = content().json(testSavedEmpAsJSON);

		// run the request and check both matchers
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	public void testCreate2() throws Exception {
		// URL body method headers
		Emp testEmp = new Emp(20, "Daffy", "Toon World", "Male");
		String testEmpAsJSON = this.mapper.writeValueAsString(testEmp);
		RequestBuilder req = post("/emp/create").content(testEmpAsJSON).contentType(MediaType.APPLICATION_JSON);

		Emp testSavedEmp = new Emp(2, 20, "Daffy", "Toon World", "Male");
		String testSavedEmpAsJSON = this.mapper.writeValueAsString(testSavedEmp);
		// this will check the status code of my response
		ResultMatcher checkStatus = status().isCreated();
		// this will check the body of the response
		ResultMatcher checkBody = content().json(testSavedEmpAsJSON);

		// run the request and check both matchers
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	public void testReadById() throws Exception {
		RequestBuilder req = get("/emp/readById/1");

		ResultMatcher checkStatus = status().isOk();
		Emp savedEmp = new Emp(1, 18, "Duck Dodgers", "space", "male");
		String savedEmpAsJSON = this.mapper.writeValueAsString(savedEmp);

		ResultMatcher checkBody = content().json(savedEmpAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testReadAll() throws Exception {
		Emp entry = new Emp(1L, 18, "Duck Dodgers", "space", "male");
		List<Emp> emps = new ArrayList<>();
		emps.add(entry);
		String empsOutputAsJson = this.mapper.writeValueAsString(emps);
		
		this.mvc.perform(get("/emp/readAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(empsOutputAsJson));
	}
	
	@Test
	public void testUpdate() throws Exception {
		Emp savedEmp = new Emp(19, "Duck Dodgers", "space", "male");
		String savedEmpAsJson = this.mapper.writeValueAsString(savedEmp);
		Emp savedEmp2 = new Emp(1,19, "Duck Dodgers", "space", "male");
		String savedEmpAsJson2 = this.mapper.writeValueAsString(savedEmp2);
		
		this.mvc.perform(put("/emp/update/1").content(savedEmpAsJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted())
				.andExpect(content().json(savedEmpAsJson2));
	}
	
	@Test
	public void testDelete() throws Exception {	
		this.mvc.perform(delete("/emp/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
//	@Test
//	public void testDelete2() throws Exception {	
//		this.mvc.perform(delete("/emp/delete/100")
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isNotFound());
//	}
	
	
	
	
	
	
}
