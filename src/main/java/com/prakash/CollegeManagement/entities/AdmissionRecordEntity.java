package com.prakash.CollegeManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Admission_Record")
public class AdmissionRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(nullable = false)
    private String name ;
    @Column(nullable = false)
    private Integer fees;

    @OneToOne(mappedBy = "studentAdmissionRecord")
    AdmissionRecordEntity admissionRecordOfStudent;
}
