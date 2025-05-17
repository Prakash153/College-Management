package com.prakash.CollegeManagement.controllers;

import com.prakash.CollegeManagement.dto.ProfessorDTO;
import com.prakash.CollegeManagement.dto.StudentDTO;
import com.prakash.CollegeManagement.dto.SubjectDTO;
import com.prakash.CollegeManagement.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    ProfessorService professorService;

    @GetMapping("/{professorId}")
    public ResponseEntity<ProfessorDTO> getProfessorById(@PathVariable Long professorId) {
        return ResponseEntity.ok(professorService.getProfessorById(professorId));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> getAllProfessors() {
        return ResponseEntity.ok(professorService.getAllProfessors());
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> saveProfessor(@RequestBody ProfessorDTO inputProfessor) {

        return new ResponseEntity<>(professorService.saveProfessor(inputProfessor), HttpStatus.CREATED);
    }

    @PutMapping("/{professorId}")
    public ResponseEntity<ProfessorDTO> updateProfessor(@PathVariable Long professorId, @RequestBody ProfessorDTO updatedProfessor) {
        return ResponseEntity.ok(professorService.updateProfessor(professorId, updatedProfessor));
    }

    @DeleteMapping("/{professorId}")
    public ResponseEntity<?> deleteProfessor(@PathVariable Long professorId) {
        professorService.deleteProfessor(professorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/{professorId}/professorAssignedToStudent/{studentId}")
    public ResponseEntity<ProfessorDTO> assignProfessorToStudent(@PathVariable Long professorId,
                                                                 @PathVariable Long studentId) {
        return ResponseEntity.ok(professorService.assignProfessorToStudent(professorId, studentId));
    }

    @PutMapping("/{professorId}/assignedProfessorForSubject/{subjectId}")
    public ResponseEntity<ProfessorDTO> assignSubjectToProfessor(@PathVariable Long professorId,
                                                                 @PathVariable Long subjectId) {
        return ResponseEntity.ok(professorService.assignSubjectToProfessor(professorId, subjectId));
    }

    @GetMapping("/{professorId}/assignedStudents")
    public ResponseEntity<List<StudentDTO>> getAssignedStudents(@PathVariable Long professorId) {
        return ResponseEntity.ok(professorService.getAssignedStudents(professorId));
    }

    @GetMapping("/{professorId}/assignedSubjects")
    public ResponseEntity<List<SubjectDTO>> getAssignedSubjects(@PathVariable Long professorId) {
        return ResponseEntity.ok(professorService.getAssignedSubjects(professorId));
    }


}
