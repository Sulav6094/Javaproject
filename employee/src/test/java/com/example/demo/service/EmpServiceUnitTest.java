package com.example.demo.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entity.Emp;
import com.example.demo.repo.EmpRepo;

@SpringBootTest

public class EmpServiceUnitTest {
	
	@Autowired
	private EmpService service;
	
	@MockBean
	private EmpRepo repo;
	
	@Test
	public void createEmpTest() {
		Emp input=new Emp(18, "Sulav", "Pond", "Male");
		Emp output= new Emp(1L,18, "Sulav", "Pond", "Male");
		
		Mockito.when(this.repo.save(input)).thenReturn(output);
		
		assertEquals(output,this.service.create(input));
		//verifying runs once and then saves input
		Mockito.verify(this.repo,Mockito.times(1)).save(input);
				
	}
	
	@Test
	public void readByIdTest() {
		Optional<Emp> optionalOutput =Optional.of(new Emp(1L,19, "Sulav", "Pond", "Male"));
		Emp output=new Emp(1L,19, "Sulav", "Pond", "Male");
		Mockito.when(this.repo.findById(Mockito.anyLong())).thenReturn(optionalOutput);
		assertEquals(output,this.service.readById(Mockito.anyLong()));
		Mockito.verify(this.repo,Mockito.times(1)).findById(Mockito.anyLong());
	}
	
	@Test
	public void updateTest() {
		Emp input=new Emp(19, "Sulav", "Pond", "Male");
		Optional<Emp> optionalOutput =Optional.of(new Emp(1L,18, "Sulav", "Pond", "Male"));
		Emp output= new Emp(1L,19, "Sulav", "Pond", "Male");
		
		Mockito.when(this.repo.findById(Mockito.anyLong())).thenReturn(optionalOutput);
		Mockito.when(this.repo.saveAndFlush(input)).thenReturn(output);
		
		assertEquals(output,this.service.update(Mockito.anyLong(),input));
		//verifying runs once 
		Mockito.verify(this.repo,Mockito.times(1)).findById(Mockito.anyLong());
		Mockito.verify(this.repo,Mockito.times(1)).saveAndFlush(output);
				
	}
	
	@Test
	public void deleteFalseTest() {
	
		
		Mockito.when(this.repo.existsById(Mockito.anyLong())).thenReturn(true);
		
		assertEquals(false,this.service.delete(Mockito.anyLong()));
		//verifying runs once 
		Mockito.verify(this.repo,Mockito.times(1)).deleteById(Mockito.anyLong());
		Mockito.verify(this.repo,Mockito.times(1)).existsById(Mockito.anyLong());
	}
		
	@Test
	public void deleteTrueTest(){

		Mockito.when(this.repo.existsById(Mockito.anyLong())).thenReturn(false);
		
		assertTrue(this.service.delete(Mockito.anyLong()));
		Mockito.verify(this.repo,Mockito.times(1)).deleteById(Mockito.anyLong());
		Mockito.verify(this.repo,Mockito.times(1)).existsById(Mockito.anyLong());
	}
		
	
	
	@Test
	public void readAllTest() {
	Emp entry = new Emp(1L, 20, "Donald", "Disneyworld", "Male");
	List<Emp> emps = new ArrayList<>();
	emps.add(entry);
	Mockito.when(this.repo.findAll()).thenReturn(emps);
	assertEquals(emps,this.service.readAll());
	Mockito.verify(this.repo,Mockito.times(1)).findAll();
	
	}

}
