package com.example.demo.service;

import java.util.List;

public interface ServiceMethods<T> {
	T create(T emp);
	
	List<T> readAll();
	
	T readById(long id);
	
	T update(long id, T emp);
	
	boolean delete(long id);

}
