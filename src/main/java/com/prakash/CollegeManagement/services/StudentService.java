package com.prakash.CollegeManagement.services;

import com.prakash.CollegeManagement.entities.StudentEntity;
import com.prakash.CollegeManagement.entities.SubjectEntity;
import com.prakash.CollegeManagement.repositories.StudentRepository;
import com.prakash.CollegeManagement.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public StudentEntity getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentEntity saveStudent(StudentEntity inputStudent) {
        return studentRepository.save(inputStudent);
    }

    public StudentEntity allocateSubjectToStudent(Long studentId, Long subjectId) {
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(subjectId);


   return studentEntity.flatMap(student ->
           subjectEntity.map(subject -> {
               student.getSubjectsStudy().add(subject);
               studentRepository.save(student);
               subject.getStudentSubject().add(student);
               return student;
           })).orElse(null);
    }

    public StudentEntity createAdmissionRecordOfStudent(Long studentId) {
return null ;
    }
}
