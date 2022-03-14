package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Emp;
import com.example.demo.repo.EmpRepo;

@Service
public class EmpService implements ServiceMethods<Emp> {
	// NOT making a new object, creating a variable of the type EmpRepo
	private EmpRepo repo;

	// Constructor
	public EmpService(EmpRepo repo) {
				this.repo = repo;
			}

	@Override
	public Emp create(Emp emp) {
		// TODO Auto-generated method stub
		return this.repo.save(emp);
	}

	@Override
	public List<Emp> readAll() {
		// TODO Auto-generated method stub
		return this.repo.findAll();
	}

	@Override
	public Emp readById(long id) {
		// TODO Auto-generated method stub
		Optional<Emp> getEmp = this.repo.findById(id);
		if(getEmp.isPresent()) {
			return getEmp.get();
		}
		return null;
	}

	@Override
	public Emp update(long id, Emp emp) {
		// TODO Auto-generated method stub
		Optional<Emp> existingEmp = this.repo.findById(id);
		if(existingEmp.isPresent()) {
			Emp exists = existingEmp.get();
			exists.setAge(emp.getAge());
			exists.setGender(emp.getGender());
			exists.setAddress(emp.getAddress());
			exists.setName(emp.getName());
			
			return this.repo.saveAndFlush(exists);
		}
		return null;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}

