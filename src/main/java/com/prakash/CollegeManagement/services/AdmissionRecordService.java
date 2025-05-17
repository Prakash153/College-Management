package com.prakash.CollegeManagement.services;

import com.prakash.CollegeManagement.dto.AdmissionRecordDTO;
import com.prakash.CollegeManagement.entities.AdmissionRecordEntity;
import com.prakash.CollegeManagement.repositories.AdmissionRecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmissionRecordService {
    @Autowired
    AdmissionRecordRepository admissionRecordRepository;

    @Autowired
    ModelMapper modelMapper;


    public AdmissionRecordDTO getAdmissionRecordById(Long studentId) {
        AdmissionRecordEntity admissionRecordEntity =  admissionRecordRepository.findById(studentId).orElse(null);
    return modelMapper.map(admissionRecordEntity,AdmissionRecordDTO.class);
    }

    public AdmissionRecordDTO createAdmissionRecord(AdmissionRecordDTO inputAdmissionRecord) {
       AdmissionRecordEntity saveAdmissionRecord = modelMapper.map(inputAdmissionRecord,AdmissionRecordEntity.class);
        AdmissionRecordEntity savedAdmissionRecord =  admissionRecordRepository.save(saveAdmissionRecord);
        return modelMapper.map(savedAdmissionRecord,AdmissionRecordDTO.class);
    }


}
