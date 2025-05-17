package com.prakash.CollegeManagement.controllers;

import com.prakash.CollegeManagement.dto.ProfessorDTO;
import com.prakash.CollegeManagement.dto.StudentDTO;
import com.prakash.CollegeManagement.dto.SubjectDTO;
import com.prakash.CollegeManagement.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subjects")
public class SubjectController {
    @Autowired
    SubjectService subjectService;


    @GetMapping("/{subjectId}")
    public ResponseEntity<SubjectDTO>   getSubjectById(@PathVariable Long subjectId) {
        return ResponseEntity.ok(subjectService.getSubjectById(subjectId))  ;
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>>   getAllSubjects() {

        return ResponseEntity.ok(subjectService.getAllSubjects())  ;
    }

    @PostMapping
    public ResponseEntity<SubjectDTO>   saveSubject(@RequestBody SubjectDTO inputSubject) {
        return new ResponseEntity<>(subjectService.saveSubject(inputSubject) , HttpStatus.CREATED)  ;
    }

    @PutMapping("/{subjectId}")
    public ResponseEntity<SubjectDTO>   updateSubject(@PathVariable Long subjectId, @RequestBody SubjectDTO updateSubject) {
        return ResponseEntity.ok(subjectService.updateSubject(subjectId, updateSubject))  ;
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<?>   deleteSubject(@PathVariable Long subjectId) {
          subjectService.deleteSubject(subjectId);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{subjectId}/assignedProfessor")
    public ResponseEntity<ProfessorDTO>   getAssignedProfessorForSubject(@PathVariable Long subjectId) {
        return ResponseEntity.ok(subjectService.getAssignedProfessorForSubject(subjectId))  ;
    }

    @GetMapping("{subjectId}/allocatedStudents")
    public ResponseEntity<List<StudentDTO>>   getAllocatedStudentsToSubject(@PathVariable Long subjectId) {
        return ResponseEntity.ok(subjectService.getAllocatedStudentsToSubject(subjectId))  ;
    }
}
