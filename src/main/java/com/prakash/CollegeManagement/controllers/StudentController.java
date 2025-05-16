package com.prakash.CollegeManagement.controllers;


import com.prakash.CollegeManagement.entities.StudentEntity;
import com.prakash.CollegeManagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/{studentId}")
    public StudentEntity getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping
    public List<StudentEntity> getAllStudent() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public StudentEntity saveStudent(@RequestBody StudentEntity inputStudent) {
        return studentService.saveStudent(inputStudent);
    }

    @PutMapping("/{studentId}/allocateSubjectToStudent/{subjectId}")
    public StudentEntity allocateSubjectToStudent(@PathVariable Long studentId, @PathVariable Long subjectId) {
        return studentService.allocateSubjectToStudent(studentId, subjectId);
    }

    @PutMapping("/admissionRecordOfStudent/{studentId}")
    public StudentEntity createAdmissionRecordOfStudent(@PathVariable Long studentId) {
        return studentService.createAdmissionRecordOfStudent(studentId);
    }
}
