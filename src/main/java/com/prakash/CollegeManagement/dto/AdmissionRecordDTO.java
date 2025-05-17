package com.prakash.CollegeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdmissionRecordDTO {
    private Long id ;
    private String name ;
    private Integer fees;
}
