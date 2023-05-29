package com.java.springboot.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.springboot.exception.ResourceNotFoundException;
import com.java.springboot.model.Emp;
import com.java.springboot.repository.EmpRepository;

@CrossOrigin(origins = "*")  //การกำหนดว่า พาทไหนสามารถขอ api ได้
@RestController
@RequestMapping("/api/v1/")
public class EmpController {
	
	@Autowired
	private EmpRepository empRepository;

	//get All Emp
	@GetMapping("/emp")
	public List<Emp> getAllEmp(){
		return empRepository.findAll();
	}
	
	//create Emp rest api
	@PostMapping("/emp")
	public Emp createEmp(@RequestBody Emp emp) {
		return empRepository.save(emp);
	}
	
	//get emp by id rest api
	@GetMapping("emp/{id}")
	public ResponseEntity<Emp> getEmpById(@PathVariable Long id){
		
		Emp emp = empRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Emp not exist with id :" + id));
		
		return ResponseEntity.ok(emp);
	}
	
	//update emp rest api
	
	@PutMapping("/emp/{id}")
	public ResponseEntity<Emp> updateEmp(@PathVariable Long id,@RequestBody Emp empDetails){
		
		Emp emp = empRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Emp not exist with id :" + id));
		
		emp.setFirstName(empDetails.getFirstName());
		emp.setLastName(empDetails.getLastName());
		emp.setEmail(empDetails.getEmail());
		
		Emp updateEmp = empRepository.save(emp);
		return ResponseEntity.ok(updateEmp);
	}
	
	// delete emp rest api
	@DeleteMapping("/emp/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmp(@PathVariable Long id){
		Emp emp = empRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Emp not exist with id :" + id));
		
		empRepository.delete(emp);
		Map<String, Boolean> response = new HashMap<>();
		response.put("delete", Boolean.TRUE);
		return  ResponseEntity.ok(response);
	}

}
