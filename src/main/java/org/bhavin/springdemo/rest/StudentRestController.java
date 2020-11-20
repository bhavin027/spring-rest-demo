package org.bhavin.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.bhavin.springdemo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	// define end point for '/students' to return list of students
	@GetMapping("/students")
	public List<Student> getStudents() {
		
		List<Student> tempStudent = new ArrayList<>();
		tempStudent.add(new Student("Wayne","Rooney"));
		tempStudent.add(new Student("Cristiano","Ronaldo"));
		tempStudent.add(new Student("Lionel","Messi"));
		
		return tempStudent;
	}
	
}
