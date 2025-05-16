package com.prakash.CollegeManagement.services;

import com.prakash.CollegeManagement.entities.ProfessorEntity;
import com.prakash.CollegeManagement.entities.StudentEntity;
import com.prakash.CollegeManagement.entities.SubjectEntity;
import com.prakash.CollegeManagement.repositories.ProfessorRepository;
import com.prakash.CollegeManagement.repositories.StudentRepository;
import com.prakash.CollegeManagement.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;


    public ProfessorEntity getProfessorById(Long professorId) {
        return professorRepository.findById(professorId).orElse(null);
    }

    public List<ProfessorEntity> getAllProfessors() {
        return professorRepository.findAll();
    }

    public ProfessorEntity saveProfessor(ProfessorEntity inputProfessor) {
        return professorRepository.save(inputProfessor);
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
