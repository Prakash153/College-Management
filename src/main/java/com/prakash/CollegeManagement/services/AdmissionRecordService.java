package com.prakash.CollegeManagement.services;

import com.prakash.CollegeManagement.dto.AdmissionRecordDTO;
import com.prakash.CollegeManagement.entities.AdmissionRecordEntity;
import com.prakash.CollegeManagement.exceptions.ResourceNotFoundException;
import com.prakash.CollegeManagement.repositories.AdmissionRecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdmissionRecordService {
    @Autowired
    AdmissionRecordRepository admissionRecordRepository;

    @Autowired
    ModelMapper modelMapper;


    public AdmissionRecordDTO enrollStudent(AdmissionRecordDTO studentDetails) {
        AdmissionRecordEntity admissionRecordEntity = modelMapper.map(studentDetails, AdmissionRecordEntity.class);
        return modelMapper.map(admissionRecordRepository.save(admissionRecordEntity), AdmissionRecordDTO.class);
    }

    public List<AdmissionRecordDTO> getAllEnrolledStudents() {
        List<AdmissionRecordEntity> admissionRecordEntities = admissionRecordRepository.findAll();
        return admissionRecordEntities
                .stream()
                .map(admissionRecordEntity -> modelMapper.map(admissionRecordEntity, AdmissionRecordDTO.class))
                .collect(Collectors.toList());
    }

    public AdmissionRecordDTO getEnrolledStudentById(Long enrollmentId) {
        isExistedByIdAdmissionRecord(enrollmentId);
        AdmissionRecordEntity admissionRecordEntity = admissionRecordRepository.findById(enrollmentId).get();
        return modelMapper.map(admissionRecordEntity, AdmissionRecordDTO.class);
    }

    public AdmissionRecordDTO updateEnrolledStudentDetails(Long enrollmentId, AdmissionRecordDTO updatedStudentDetails) {
        isExistedByIdAdmissionRecord(enrollmentId);
        AdmissionRecordEntity admissionRecordEntity = modelMapper.map(updatedStudentDetails, AdmissionRecordEntity.class);
        admissionRecordEntity.setId(enrollmentId);
        return modelMapper.map(admissionRecordRepository.save(admissionRecordEntity), AdmissionRecordDTO.class);
    }

    public void deleteStudentRecord(Long enrollmentId) {
        isExistedByIdAdmissionRecord(enrollmentId);
        admissionRecordRepository.deleteById(enrollmentId);
    }
    private void isExistedByIdAdmissionRecord(Long id){
        boolean isExist = admissionRecordRepository.existsById(id);
        if(!isExist) throw new ResourceNotFoundException("Admission record not found with id "+id);
    }
}
