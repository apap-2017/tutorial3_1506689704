package com.example.tutorial3.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.tutorial3.service.inMemoryStudentService;
import com.example.tutorial3.service.StudentService;
import com.example.tutorial3.model.StudentModel;

@Controller
public class StudentController {
	private final StudentService studentService;
	
	public StudentController(){
		studentService = new inMemoryStudentService();
	}
	
	@RequestMapping("/student/add")
	public String add(@RequestParam(value= "npm", required = true) String npm,
	@RequestParam(value = "name", required = true) String name,
	@RequestParam(value = "gpa", required = true) double gpa){
		
	StudentModel student = new StudentModel(npm,name,gpa);
	studentService.addStudent(student);
	return "add";
	}
	
	@RequestMapping("/student/viewall")
		public String viewAll(Model model) {
			List<StudentModel> students = studentService.selectAllStudents();
			model.addAttribute("students",students);
			return "viewall";
	}
	
	@RequestMapping(value = {"/student/view", "student/view/{npm}"})
	public String viewPV(@PathVariable (value ="npm", required = false) String npm, Model model) {
		StudentModel student = studentService.selectStudent(npm);
		if(student!=null) {
			model.addAttribute("student",student);
			return "viewPV";
		}else {
			return "salahview";
		}
	}

	
	@RequestMapping(value = {"/student/delete", "student/delete/{npm}"})
	public String delete(@PathVariable (value ="npm", required = false) String npm, Model model) {
		StudentModel student = studentService.selectStudent(npm);
		if(student!=null) {
			List<StudentModel> students = studentService.selectAllStudents();
			students.remove(student);
			return "delete";
		}else {
			return "salahdelete";
		}	
	}


	
	
	
	
}
