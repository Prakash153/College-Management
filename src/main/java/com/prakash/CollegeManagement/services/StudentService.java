package com.prakash.CollegeManagement.services;

import com.prakash.CollegeManagement.dto.ProfessorDTO;
import com.prakash.CollegeManagement.dto.StudentDTO;
import com.prakash.CollegeManagement.dto.SubjectDTO;
import com.prakash.CollegeManagement.entities.AdmissionRecordEntity;
import com.prakash.CollegeManagement.entities.ProfessorEntity;
import com.prakash.CollegeManagement.entities.StudentEntity;
import com.prakash.CollegeManagement.entities.SubjectEntity;
import com.prakash.CollegeManagement.exceptions.ResourceNotFoundException;
import com.prakash.CollegeManagement.repositories.AdmissionRecordRepository;
import com.prakash.CollegeManagement.repositories.StudentRepository;
import com.prakash.CollegeManagement.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        isExistByIdStudent(studentId);
        StudentEntity studentEntity = studentRepository.findById(studentId).orElse(null);
        return modelMapper.map(studentEntity, StudentDTO.class);

    }

    public List<StudentDTO> getAllStudents() {

        List<StudentEntity> studentEntityList = studentRepository.findAll();
        return studentEntityList
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDTO.class))
                .collect(Collectors.toList());
    }

    public StudentDTO saveStudent(StudentDTO inputStudent) {
        StudentEntity saveStudentEntity = modelMapper.map(inputStudent, StudentEntity.class);
        StudentEntity savedStudentEntity = studentRepository.save(saveStudentEntity);
        return modelMapper.map(savedStudentEntity, StudentDTO.class);
    }

    public StudentDTO updateStudent(Long studentId, StudentDTO updateStudent) {
        isExistByIdStudent(studentId);
        StudentEntity studentEntity = modelMapper.map(updateStudent, StudentEntity.class);
        studentEntity.setId(studentId);
        studentRepository.save(studentEntity);
        return modelMapper.map(studentEntity, StudentDTO.class);
    }

    public void deleteStudent(Long studentId) {
        isExistByIdStudent(studentId);
        studentRepository.deleteById(studentId);
    }


    public StudentDTO allocateSubjectToStudent(Long studentId, Long subjectId) {
        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id : " + studentId));
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id : " + subjectId));


        studentEntity.getSubjects().add(subjectEntity);
        return modelMapper.map(studentRepository.save(studentEntity),StudentDTO.class);
    }

    public StudentDTO createAdmissionRecordOfStudent(Long studentId, Long enrollmentId) {
        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id : " + studentId));
        AdmissionRecordEntity admissionRecordEntity = admissionRecordRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Admission Record not found with id : " + enrollmentId));


         studentEntity.setAdmissionRecord(admissionRecordEntity);
         return modelMapper.map(studentRepository.save(studentEntity),StudentDTO.class);

    }


    public List<ProfessorDTO> getAssignedProfessors(Long studentId) {
        isExistByIdStudent(studentId);
        List<ProfessorEntity> professorEntities = studentRepository.findById(studentId).get().getProfessors().stream().toList();
        return professorEntities
                .stream()
                .map(professorEntity -> modelMapper.map(professorEntity, ProfessorDTO.class))
                .collect(Collectors.toList());
    }

    public List<SubjectDTO> getAssignedSubjects(Long studentId) {
        isExistByIdStudent(studentId);
        List<SubjectEntity> subjectEntities = studentRepository.findById(studentId).get().getSubjects().stream().toList();
        return subjectEntities
                .stream()
                .map(subjectEntity -> modelMapper.map(subjectEntity, SubjectDTO.class))
                .collect(Collectors.toList());
    }

    private void isExistByIdStudent(Long id ){
        boolean isExist = studentRepository.existsById(id);
        if (!isExist) throw new ResourceNotFoundException("Student not found with id : " + id);
    }



}
