package com.prakash.CollegeManagement.services;

import com.prakash.CollegeManagement.entities.SubjectEntity;
import com.prakash.CollegeManagement.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;
    public SubjectEntity getSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId).orElse(null);
    }

    public List<SubjectEntity> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public SubjectEntity saveSubject(SubjectEntity inputSubject) {
        return subjectRepository.save(inputSubject);
    }
}
