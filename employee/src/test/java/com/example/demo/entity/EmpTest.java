package com.example.demo.entity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class EmpTest {
	@Test
	public void testEquals() {
		EqualsVerifier.forClass(Emp.class).usingGetClass().verify();
	}

	@Test
	public void getAndSetTest() {
		// Create empty emp object
		Emp emp = new Emp();

		// Use the setter methods to add values to each field, so we can check the
		// setters work
		emp.setId(1L);
		emp.setAge(18);
		emp.setGender("Female");
		emp.setAddress("Lake");
		emp.setName("Empy");

		// make sure after the setters, they actually set the values and are not null
		assertNotNull(emp.getAge());
		assertNotNull(emp.getId());
		assertNotNull(emp.getGender());
		assertNotNull(emp.getName());
		assertNotNull(emp.getAddress());

		// make sure that when we use the getters, they get the correct value
		assertEquals(emp.getAge(), 18);
		assertEquals(emp.getId(), 1L);
		assertEquals(emp.getGender(), "Female");
		assertEquals(emp.getName(), "Empy");
		assertEquals(emp.getAddress(), "Lake");
	}

	@Test
	public void allArgsConstructor() {
		Emp emp = new Emp(1L, 19, "Jordan", "House pond", "Male");

		// make sure after the setters, they actually set the values and are not null
		assertNotNull(emp.getAge());
		assertNotNull(emp.getId());
		assertNotNull(emp.getGender());
		assertNotNull(emp.getName());
		assertNotNull(emp.getAddress());

		// make sure that when we use the getters, they get the correct value
		assertEquals(emp.getAge(), 19);
		assertEquals(emp.getId(), 1L);
		assertEquals(emp.getGender(), "Male");
		assertEquals(emp.getName(), "Jordan");
		assertEquals(emp.getAddress(), "House pond");

	}
	

}

