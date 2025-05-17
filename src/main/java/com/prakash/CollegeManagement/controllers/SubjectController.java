package com.prakash.CollegeManagement.controllers;

import com.prakash.CollegeManagement.dto.SubjectDTO;
import com.prakash.CollegeManagement.entities.SubjectEntity;
import com.prakash.CollegeManagement.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subjects")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @GetMapping("/{subjectId}")
    public SubjectDTO getSubjectById(@PathVariable Long subjectId){
        return subjectService.getSubjectById(subjectId);
    }

    @GetMapping
    public List<SubjectDTO> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    @PostMapping
    public SubjectDTO saveSubject(@RequestBody SubjectDTO inputSubject){
        return subjectService.saveSubject(inputSubject);
    }
}
