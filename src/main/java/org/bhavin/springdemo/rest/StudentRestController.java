package org.bhavin.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bhavin.springdemo.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> theStudent;
	
	// define @PostContruct to load the student data... only once
	
	@PostConstruct
	public void loadData() {
		
		theStudent = new ArrayList<>();
		theStudent.add(new Student("Wayne","Rooney"));
		theStudent.add(new Student("Cristiano","Ronaldo"));
		theStudent.add(new Student("Lionel","Messi"));
		
	}
	
	// define end point for '/students' to return list of students
	@GetMapping("/students")
	public List<Student> getStudents() {
		
		return theStudent;
	}
	
	// define end point for single student
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		if((studentId < 0) || (studentId >= theStudent.size()) ) {
			throw new StudentNotFoundException("Student id: "+studentId+" not found");
			
		}
		
		return theStudent.get(studentId);
	}
	
	// add an exception handler
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException (StudentNotFoundException exc) {
		
		StudentErrorResponse error = new StudentErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	// another exception handler to catch any exception
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException (Exception exc) {
		
		StudentErrorResponse error = new StudentErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
}








