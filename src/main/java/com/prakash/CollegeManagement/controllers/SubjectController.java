package com.prakash.CollegeManagement.controllers;

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
    public SubjectEntity getSubjectById(@PathVariable Long subjectId){
        return subjectService.getSubjectById(subjectId);
    }

    @GetMapping
    public List<SubjectEntity> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    @PostMapping
    public SubjectEntity saveSubject(@RequestBody SubjectEntity inputSubject){
        return subjectService.saveSubject(inputSubject);
    }
}
