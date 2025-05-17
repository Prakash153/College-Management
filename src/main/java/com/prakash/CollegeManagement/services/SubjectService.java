package com.prakash.CollegeManagement.services;

import com.prakash.CollegeManagement.dto.SubjectDTO;
import com.prakash.CollegeManagement.entities.SubjectEntity;
import com.prakash.CollegeManagement.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    ModelMapper modelMapper;

    public SubjectDTO getSubjectById(Long subjectId) {
        SubjectEntity subjectEntity =  subjectRepository.findById(subjectId).orElse(null);
        return modelMapper.map(subjectEntity,SubjectDTO.class);
    }

    public List<SubjectDTO> getAllSubjects() {
        List<SubjectEntity> subjectEntityList =  subjectRepository.findAll();
        return subjectEntityList
                .stream()
                .map(subjectEntity -> modelMapper.map(subjectEntity,SubjectDTO.class))
                .collect(Collectors.toList());
    }

    public SubjectDTO saveSubject(SubjectDTO inputSubject) {
        SubjectEntity saveSubject = modelMapper.map(inputSubject,SubjectEntity.class);
        SubjectEntity savedSubject = subjectRepository.save(saveSubject);
        return modelMapper.map(savedSubject,SubjectDTO.class);
    }
}
