package com.prakash.CollegeManagement.services;

import com.prakash.CollegeManagement.dto.StudentDTO;
import com.prakash.CollegeManagement.entities.AdmissionRecordEntity;
import com.prakash.CollegeManagement.entities.StudentEntity;
import com.prakash.CollegeManagement.entities.SubjectEntity;
import com.prakash.CollegeManagement.repositories.AdmissionRecordRepository;
import com.prakash.CollegeManagement.repositories.StudentRepository;
import com.prakash.CollegeManagement.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    AdmissionRecordRepository admissionRecordRepository;

    @Autowired
    ModelMapper modelMapper;

    public StudentDTO getStudentById(Long studentId) {
         StudentEntity studentEntity =  studentRepository.findById(studentId).orElse(null);
         return modelMapper.map(studentEntity,StudentDTO.class);

    }

    public List<StudentDTO> getAllStudents() {

        List<StudentEntity> studentEntityList =  studentRepository.findAll();
        return studentEntityList
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity,StudentDTO.class))
                .collect(Collectors.toList());
    }

    public StudentDTO saveStudent(StudentDTO inputStudent) {
        StudentEntity saveStudentEntity = modelMapper.map(inputStudent,StudentEntity.class);
        StudentEntity savedStudentEntity = studentRepository.save(saveStudentEntity);
        return modelMapper.map(savedStudentEntity,StudentDTO.class);
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

    public StudentEntity createAdmissionRecordOfStudent(Long studentId , Long enrollmentId) {
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
        Optional<AdmissionRecordEntity> admissionRecordEntity = admissionRecordRepository.findById(enrollmentId);
    return          studentEntity.flatMap(student ->
                 admissionRecordEntity.map(admissionRecord -> {
                     student.setAdmissionRecord(admissionRecord);
                     studentRepository.save(student);
                     return student;
                 })).orElse(null);

    }
}
