package com.prakash.CollegeManagement.services;

import com.prakash.CollegeManagement.dto.ProfessorDTO;
import com.prakash.CollegeManagement.dto.StudentDTO;
import com.prakash.CollegeManagement.dto.SubjectDTO;
import com.prakash.CollegeManagement.entities.ProfessorEntity;
import com.prakash.CollegeManagement.entities.StudentEntity;
import com.prakash.CollegeManagement.entities.SubjectEntity;
import com.prakash.CollegeManagement.exceptions.ResourceNotFoundException;
import com.prakash.CollegeManagement.repositories.ProfessorRepository;
import com.prakash.CollegeManagement.repositories.StudentRepository;
import com.prakash.CollegeManagement.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    ModelMapper modelMapper;

    public ProfessorDTO getProfessorById(Long professorId) {
        isExistByIdProfessor(professorId);
        ProfessorEntity professorEntity = professorRepository.findById(professorId).get();
        return modelMapper.map(professorEntity, ProfessorDTO.class);
    }

    public List<ProfessorDTO> getAllProfessors() {
        List<ProfessorEntity> professorEntityList = professorRepository.findAll();

        return professorEntityList
                .stream()
                .map(professorEntity -> modelMapper.map(professorEntity, ProfessorDTO.class))
                .collect(Collectors.toList());
    }

    public ProfessorDTO saveProfessor(ProfessorDTO inputProfessor) {
        ProfessorEntity saveProfessorEntity = modelMapper.map(inputProfessor, ProfessorEntity.class);

        ProfessorEntity savedprofessorEntity = professorRepository.save(saveProfessorEntity);
        return modelMapper.map(savedprofessorEntity, ProfessorDTO.class);
    }

    public ProfessorDTO updateProfessor(Long professorId, ProfessorDTO updatedProfessor) {
        isExistByIdProfessor(professorId);
        ProfessorEntity professorEntity = modelMapper.map(updatedProfessor, ProfessorEntity.class);
        professorEntity.setId(professorId);
        return modelMapper.map(professorRepository.save(professorEntity), ProfessorDTO.class);

    }

    public void deleteProfessor(Long professorId) {
        isExistByIdProfessor(professorId);
        professorRepository.deleteById(professorId);
    }

    public ProfessorDTO assignProfessorToStudent(Long professorId, Long studentId) {

        ProfessorEntity professorEntity = professorRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id : " + professorId));
        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id : " + studentId));

        professorEntity.getStudents().add(studentEntity);
        return modelMapper.map(professorRepository.save(professorEntity), ProfessorDTO.class);
    }

    public ProfessorDTO assignSubjectToProfessor(Long professorId, Long subjectId) {

        ProfessorEntity professorEntity = professorRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id : " + professorId));
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id : " + subjectId));

        professorEntity.getSubjects().add(subjectEntity);

        return modelMapper.map(professorRepository.save(professorEntity), ProfessorDTO.class);
    }

    public List<StudentDTO> getAssignedStudents(Long professorId) {
        isExistByIdProfessor(professorId);
        List<StudentEntity> studentEntities = professorRepository.findById(professorId).get().getStudents().stream().toList();

        return studentEntities
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDTO.class))
                .collect(Collectors.toList());
    }

    public List<SubjectDTO> getAssignedSubjects(Long professorId) {
        isExistByIdProfessor(professorId);
        List<SubjectEntity> subjectEntities = professorRepository.findById(professorId).get().getSubjects().stream().toList();

        return subjectEntities
                .stream()
                .map(subjectEntity -> modelMapper.map(subjectEntity, SubjectDTO.class))
                .collect(Collectors.toList());
    }

    private void isExistByIdProfessor(Long id) {
        boolean isExist = professorRepository.existsById(id);
        if (!isExist) throw new ResourceNotFoundException("professor not found with id : " + id);
    }


}
