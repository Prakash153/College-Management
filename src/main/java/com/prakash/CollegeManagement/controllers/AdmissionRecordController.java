package com.prakash.CollegeManagement.controllers;

import com.prakash.CollegeManagement.dto.AdmissionRecordDTO;
import com.prakash.CollegeManagement.services.AdmissionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admissionRecord")
public class AdmissionRecordController {

    @Autowired
    AdmissionRecordService admissionRecordService;

    @PostMapping
    public ResponseEntity<AdmissionRecordDTO> enrollStudent(@RequestBody AdmissionRecordDTO studentDetails) {
          return new ResponseEntity<>(admissionRecordService.enrollStudent(studentDetails), HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<List<AdmissionRecordDTO>> getAllEnrolledStudents() {
        return  ResponseEntity.ok(admissionRecordService.getAllEnrolledStudents());
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<AdmissionRecordDTO> getEnrolledStudentById(@PathVariable Long enrollmentId) {
        return  ResponseEntity.ok(admissionRecordService.getEnrolledStudentById(enrollmentId));
    }

    @PutMapping("/{enrollmentId}")
    public ResponseEntity<AdmissionRecordDTO> updateEnrolledStudentDetails(@PathVariable Long enrollmentId, @RequestBody AdmissionRecordDTO updatedStudentDetails) {
        return  ResponseEntity.ok(admissionRecordService.updateEnrolledStudentDetails(enrollmentId, updatedStudentDetails));
    }

    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<?> deleteStudentRecord(@PathVariable Long enrollmentId) {
        admissionRecordService.deleteStudentRecord(enrollmentId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
