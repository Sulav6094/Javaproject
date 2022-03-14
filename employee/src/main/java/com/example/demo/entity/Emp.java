package com.example.demo.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Emp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	@Min(18)
	@Max(70)
	private int age;

	@Column(unique = true, nullable = false)
	private String name;

	@Column
	private String address;

	@Column
	private String gender;
	
	public Emp() {}

	public Emp(long id, @Min(18) @Max(70) int age, String name, String address, String gender) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
		this.address = address;
		this.gender = gender;
	}

	public Emp(@Min(18) @Max(70) int age, String name, String address, String gender) {
		super();
		this.age = age;
		this.name = name;
		this.address = address;
		this.gender = gender;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Emp [id=" + id + ", age=" + age + ", name=" + name + ", address=" + address + ", gender=" + gender
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, gender, address, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emp other = (Emp) obj;
		return age == other.age && Objects.equals(gender, other.gender) && Objects.equals(address, other.address)
				&& Objects.equals(name, other.name);
	}


	
	

}
