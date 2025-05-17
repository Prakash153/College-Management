package com.prakash.CollegeManagement.controllers;


import com.prakash.CollegeManagement.dto.ProfessorDTO;
import com.prakash.CollegeManagement.dto.StudentDTO;
import com.prakash.CollegeManagement.dto.SubjectDTO;
import com.prakash.CollegeManagement.entities.StudentEntity;
import com.prakash.CollegeManagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/{studentId}")
    public  ResponseEntity<StudentDTO>  getStudentById(@PathVariable Long studentId) {
        return  ResponseEntity.ok(studentService.getStudentById(studentId))  ;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>>   getAllStudent() {
        return  ResponseEntity.ok(studentService.getAllStudents())  ;
    }

    @PostMapping
    public ResponseEntity<StudentDTO>  saveStudent(@RequestBody StudentDTO inputStudent) {
         return new ResponseEntity<>( studentService.saveStudent(inputStudent), HttpStatus.CREATED) ;
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDTO>  updateStudent(@PathVariable Long studentId, @RequestBody StudentDTO updateStudent) {
        return  ResponseEntity.ok(studentService.updateStudent(studentId, updateStudent))  ;
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?>   deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{studentId}/allocateSubjectToStudent/{subjectId}")
    public ResponseEntity<StudentDTO>  allocateSubjectToStudent(@PathVariable Long studentId, @PathVariable Long subjectId) {
        return  ResponseEntity.ok(studentService.allocateSubjectToStudent(studentId, subjectId))   ;
    }

    @PutMapping("/{studentId}/admissionRecordOfStudent/{enrollmentId}")
    public ResponseEntity<StudentDTO>   createAdmissionRecordOfStudent(@PathVariable Long studentId, @PathVariable Long enrollmentId) {
        return  ResponseEntity.ok(studentService.createAdmissionRecordOfStudent(studentId, enrollmentId))  ;
    }

    @GetMapping("/{studentId}/assignedProfessors")
    public ResponseEntity<List<ProfessorDTO>>   getAssignedProfessors(@PathVariable Long studentId) {
        return  ResponseEntity.ok(studentService.getAssignedProfessors(studentId))  ;
    }

    @GetMapping("/{studentId}/assignedSubjects")
    public ResponseEntity<List<SubjectDTO>>   getAssignedSubjects(@PathVariable Long studentId) {
        return  ResponseEntity.ok(studentService.getAssignedSubjects(studentId))  ;
    }
}
