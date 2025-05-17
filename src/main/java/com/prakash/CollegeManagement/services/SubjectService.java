package com.prakash.CollegeManagement.services;

import com.prakash.CollegeManagement.dto.ProfessorDTO;
import com.prakash.CollegeManagement.dto.StudentDTO;
import com.prakash.CollegeManagement.dto.SubjectDTO;
import com.prakash.CollegeManagement.entities.ProfessorEntity;
import com.prakash.CollegeManagement.entities.StudentEntity;
import com.prakash.CollegeManagement.entities.SubjectEntity;
import com.prakash.CollegeManagement.exceptions.ResourceNotFoundException;
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
        isExistByIdSubject(subjectId);
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);
        return modelMapper.map(subjectEntity, SubjectDTO.class);
    }

    public List<SubjectDTO> getAllSubjects() {
        List<SubjectEntity> subjectEntityList = subjectRepository.findAll();
        return subjectEntityList
                .stream()
                .map(subjectEntity -> modelMapper.map(subjectEntity, SubjectDTO.class))
                .collect(Collectors.toList());
    }

    public SubjectDTO saveSubject(SubjectDTO inputSubject) {
        SubjectEntity saveSubject = modelMapper.map(inputSubject, SubjectEntity.class);
        SubjectEntity savedSubject = subjectRepository.save(saveSubject);
        return modelMapper.map(savedSubject, SubjectDTO.class);
    }


    public SubjectDTO updateSubject(Long subjectId, SubjectDTO updateSubject) {
        isExistByIdSubject(subjectId);
        SubjectEntity subjectEntity = modelMapper.map(updateSubject, SubjectEntity.class);
        subjectEntity.setId(subjectId);
        return modelMapper.map(subjectRepository.save(subjectEntity), SubjectDTO.class);
    }

    public void deleteSubject(Long subjectId) {
        isExistByIdSubject(subjectId);
        subjectRepository.deleteById(subjectId);
    }

    public ProfessorDTO getAssignedProfessorForSubject(Long subjectId) {
        isExistByIdSubject(subjectId);
        ProfessorEntity professorEntity = subjectRepository.findById(subjectId).get().getProfessor();
        return modelMapper.map(professorEntity, ProfessorDTO.class);
    }

    public List<StudentDTO> getAllocatedStudentsToSubject(Long subjectId) {
        isExistByIdSubject(subjectId);
        List<StudentEntity> studentEntities = subjectRepository.findById(subjectId).get().getStudents().stream().toList();

        return studentEntities
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDTO.class))
                .collect(Collectors.toList());
    }
    private void isExistByIdSubject(Long id ){
        boolean isExist = subjectRepository.existsById(id);
        if (!isExist) throw new ResourceNotFoundException("Subject not found with id : " + id);
    }

}
