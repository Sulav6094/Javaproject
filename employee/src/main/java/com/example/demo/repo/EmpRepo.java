package com.example.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Emp;


@Repository
public interface EmpRepo extends JpaRepository<Emp, Long>{

}
