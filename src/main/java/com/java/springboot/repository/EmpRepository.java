package com.java.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.springboot.model.Emp;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Long> {

}
