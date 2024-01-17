package com.project.student.service.impl;

import com.project.student.entities.Student;
import com.project.student.repository.StudentRepository;
import com.project.student.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudent(Long rollNo) throws Exception {
        return studentRepository.findByRollNo(rollNo).orElseThrow(() -> new Exception("Student not Found"));
    }

    @Override
    public Student createStudent(Student student) {
        return (Student) studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) throws Exception {
        Student newStudent = studentRepository.findByRollNo(student.getRollNo()).orElseThrow(() -> new Exception("Student not Found"));
        newStudent.setName(student.getName());
        newStudent.setDob(student.getDob());
        newStudent.setGender(student.getGender());
        studentRepository.save(newStudent);
        return newStudent;
    }

    @Override
    public boolean removeStudent(Long rollNo) throws Exception {
        Student student = studentRepository.findByRollNo(rollNo).orElseThrow(() -> new Exception("Student not Found"));
        studentRepository.deleteById(student.getId());
        return true;
    }
}
