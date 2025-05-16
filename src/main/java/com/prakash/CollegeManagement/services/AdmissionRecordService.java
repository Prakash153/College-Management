package com.prakash.CollegeManagement.services;

import com.prakash.CollegeManagement.entities.AdmissionRecordEntity;
import com.prakash.CollegeManagement.repositories.AdmissionRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmissionRecordService {
    @Autowired
    AdmissionRecordRepository admissionRecordRepository;
    public AdmissionRecordEntity getAdmissionRecordById(Long studentId) {
        return admissionRecordRepository.findById(studentId).orElse(null);
    }
    public AdmissionRecordEntity createAdmissionRecord(AdmissionRecordEntity inputAdmissionRecord) {
        return admissionRecordRepository.save(inputAdmissionRecord);
    }


}
