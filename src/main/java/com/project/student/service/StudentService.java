package com.project.student.service;

import com.project.student.entities.Student;

import java.util.List;

public interface StudentService {
    public List<Student> findAllStudents();
    public Student findStudent(Long rollNo) throws Exception;
    public Student createStudent(Student student);
    public Student updateStudent(Student student) throws Exception ;
    public boolean removeStudent(Long id) throws Exception;
}
