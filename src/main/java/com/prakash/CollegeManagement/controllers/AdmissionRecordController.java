package com.prakash.CollegeManagement.controllers;

import com.prakash.CollegeManagement.entities.AdmissionRecordEntity;
import com.prakash.CollegeManagement.services.AdmissionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admissionRecord")
public class AdmissionRecordController {

    @Autowired
    AdmissionRecordService admissionRecordService;

    @GetMapping("/{studentId}")
    public AdmissionRecordEntity getAdmissionRecordById(@PathVariable Long studentId){
        return admissionRecordService.getAdmissionRecordById(studentId);
    }

    @PostMapping
    public AdmissionRecordEntity createAdmissionRecord(@RequestBody AdmissionRecordEntity inputAdmissionRecord){
        return admissionRecordService.createAdmissionRecord(inputAdmissionRecord);
    }
}
