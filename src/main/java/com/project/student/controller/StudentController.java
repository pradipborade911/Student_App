package com.project.student.controller;

import com.project.student.entities.Student;
import com.project.student.service.StudentService;
import com.project.student.service.impl.StudentServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    public static final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        try {
            LOG.info("Fetching student records");
            List<Student> students = studentService.findAllStudents();
            LOG.info("Student records fetched successfully");
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching records");
        }
    }

    @GetMapping("/{rollNo}")
    public ResponseEntity<?> getStudent(@PathVariable Long rollNo) {
        try {
            LOG.info("Fetching student record for Roll no.: " + rollNo);
            Student student = studentService.findStudent(rollNo);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            LOG.info("Error occurred while fetching record");
            throw new RuntimeException("Error occurred while fetching record");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        try {
            LOG.info("Adding student to records: " + student.toString());
            studentService.createStudent(student);
            LOG.info("Record updated successfully");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            LOG.info("Error occurred while adding student to records: " + student.toString());
            throw new RuntimeException("Error occurred while adding student to record");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        try {
            LOG.info("Updating student record: " + student.toString());
            studentService.updateStudent(student);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            LOG.info("Failed to update record");
            throw new RuntimeException("Failed to update record");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            LOG.info("Removing student from record");
            studentService.removeStudent(id);
            LOG.info("Removed student successfully");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.info("Failed to remove student");
            throw new RuntimeException("Failed to remove student");
        }
    }
}
