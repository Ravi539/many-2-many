package com.springdatajpa.many2many.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdatajpa.many2many.entity.Course;
import com.springdatajpa.many2many.entity.Student;
import com.springdatajpa.many2many.repository.CourseRepository;
import com.springdatajpa.many2many.repository.StudentRepository;

@RestController
@RequestMapping("/api")
public class StudentCourseController {

	
	 @Autowired
	 private StudentRepository studentRepository;

	 @Autowired
	 private CourseRepository courseRepository;
    
	 @PostMapping("/students")
	 public ResponseEntity<Student> createStudent(@RequestBody Student newStudent) {
	     Student createdStudent = studentRepository.save(newStudent);
	     return ResponseEntity.ok(createdStudent);
	 }


	 @GetMapping("/students")
	    public List<Student> getAllStudents() {
	        return studentRepository.findAll();
	    }
    
    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found with ID: " + studentId));
        return ResponseEntity.ok(student);
    }
    
    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course newCourse) {
        Course createdCourse = courseRepository.save(newCourse);
        return ResponseEntity.ok(createdCourse);
    }
    
    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    
    @PutMapping("/students/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found with ID: " + studentId));

        student.setName(updatedStudent.getName());

        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.ok(savedStudent);
    }
    
    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found with ID: " + studentId));

        studentRepository.delete(student);
        return ResponseEntity.ok("Student deleted successfully");
    }
    
   

}
