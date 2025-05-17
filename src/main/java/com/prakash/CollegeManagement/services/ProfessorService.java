package com.prakash.CollegeManagement.services;

import com.prakash.CollegeManagement.dto.ProfessorDTO;
import com.prakash.CollegeManagement.entities.ProfessorEntity;
import com.prakash.CollegeManagement.entities.StudentEntity;
import com.prakash.CollegeManagement.entities.SubjectEntity;
import com.prakash.CollegeManagement.repositories.ProfessorRepository;
import com.prakash.CollegeManagement.repositories.StudentRepository;
import com.prakash.CollegeManagement.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        ProfessorEntity professorEntity =  professorRepository.findById(professorId).orElse(null);
        return modelMapper.map(professorEntity, ProfessorDTO.class);
    }

    public List<ProfessorDTO> getAllProfessors() {
        List<ProfessorEntity> professorEntityList = professorRepository.findAll();

        return professorEntityList
                .stream()
                .map(professorEntity -> modelMapper.map(professorEntity,ProfessorDTO.class))
                .collect(Collectors.toList());
    }

    public ProfessorDTO saveProfessor(ProfessorDTO inputProfessor) {
        ProfessorEntity saveProfessorEntity = modelMapper.map(inputProfessor,ProfessorEntity.class);

         ProfessorEntity savedprofessorEntity =  professorRepository.save(saveProfessorEntity);
         return modelMapper.map(savedprofessorEntity,ProfessorDTO.class);
    }

    public ProfessorEntity assignProfessorToStudent(Long professorId, Long studentId) {

        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);

      return   professorEntity.flatMap(professor ->
                studentEntity.map(student -> {
                    professor.getStudents().add(student);
                    professorRepository.save(professor);

                    student.getProfessors().add(professor);
                    return professor;

                })).orElse(null);

     }

    public ProfessorEntity assignProfessorForSubject(Long professorId, Long subjectId) {

        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(subjectId);

        return   professorEntity.flatMap(professor ->
            subjectEntity.map(subject -> {
                professor.getSubjects().add(subject);
                subject.setProfessorAssigned(professor);
                professorRepository.save(professor);

                return professor;
            })).orElse(null);
    }
}
