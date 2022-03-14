package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import com.example.demo.entity.Emp;
import com.example.demo.service.EmpService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EmpControllerUnitTest {
	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper map;
	
	@MockBean 
	EmpService empService;
	
	
	@Test
	public void createTest() throws Exception {
		Emp newEmp = new Emp(19, "George Orwell", "Delhi", "male");
		String newEmpJSON = this.map.writeValueAsString(newEmp);
		
		Mockito.when(this.empService.create(newEmp)).thenReturn(newEmp);
		
		mock.perform(post("/emp/create")
				.content(newEmpJSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		
	}
	
	
	@Test
	public void readAllTest() throws Exception {
		List<Emp> mockOutput = new ArrayList<Emp>();
		mockOutput.add(new Emp (1L, 18, "Joseph Heller", "Saturn", "male"));
		mockOutput.add(new Emp (2L, 19, "Thomas Hardy", "Mars","male"));
		
		String mockOutputJSON = this.map.writeValueAsString(mockOutput);
		
		Mockito.when(this.empService.readAll()).thenReturn(mockOutput);
		
		mock.perform(get("/emp/readAll"))
				.andExpect(status().isOk())
				.andExpect(content().json(mockOutputJSON));
	}
	
	
	@Test
	public void readByIdTest() throws Exception {
		long empID = 3L;
		Emp mockEmp = new Emp(3L, 18, "Joseph Heller", "Saturn", "male");
		String mockEmpJSON = this.map.writeValueAsString(mockEmp);
		
		Mockito.when(this.empService.readById(empID)).thenReturn(mockEmp);
		
		mock.perform(get("/emp/readById/3"))
				.andExpect(status().isOk())
				.andExpect(content().json(mockEmpJSON));
	}
	
	
	@Test
	public void updateTest() throws Exception {
		long empID = 4L;
		Emp newEmp = new Emp(19, "Louisa May Alcott ", "Cambridge","female");
		Emp mockEmp = new Emp(4L, 18, "Louisa May Alcott ", "Cambridge", "female");
		String newEmpJSON = this.map.writeValueAsString(newEmp);
		String mockEmpJSON = this.map.writeValueAsString(mockEmp);
		
		Mockito.when(this.empService.update(empID, newEmp)).thenReturn(mockEmp);
		
		mock.perform(put("/emp/update/4")
				.content(newEmpJSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted())
				.andExpect(content().json(mockEmpJSON));
	}
	
	
	@Test
	public void deleteTest() throws Exception {
		long invalidID = 6L;
		Mockito.when(this.empService.delete(invalidID)).thenReturn(false);
		
		
		mock.perform(delete("/emp/delete/6"))
		.andExpect(status().isNotFound());
		
		
		}
		
		
}
