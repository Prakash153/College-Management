package com.prakash.CollegeManagement.controllers;

import com.prakash.CollegeManagement.entities.ProfessorEntity;
import com.prakash.CollegeManagement.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    ProfessorService professorService;

    @GetMapping("/{professorId}")
    public ProfessorEntity getProfessorById(@PathVariable Long professorId){
        return professorService.getProfessorById(professorId);
    }
    @GetMapping
    public List<ProfessorEntity> getAllProfessors()
    {
        return professorService.getAllProfessors();
    }
    @PostMapping
    public ProfessorEntity saveProfessor(@RequestBody ProfessorEntity inputProfessor){
        return professorService.saveProfessor(inputProfessor);
    }

    @PutMapping("/{professorId}/professorAssignedToStudent/{studentId}")
    public ProfessorEntity assignProfessorToStudent(@PathVariable Long professorId,
                                                      @PathVariable Long studentId){
        return professorService.assignProfessorToStudent(professorId,studentId);
    }

    @PutMapping("/{professorId}/assignedProfessorForSubject/{subjectId}")
    public ProfessorEntity assignProfessorForSubject(@PathVariable Long professorId,
                                                      @PathVariable Long subjectId){
        return professorService.assignProfessorForSubject(professorId,subjectId);
    }

    
}
