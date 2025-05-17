package com.prakash.CollegeManagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    @JsonIgnore
    private Set<ProfessorDTO> professors;
    private Set<SubjectDTO> subjects;
    private AdmissionRecordDTO admissionRecord;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StudentDTO that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
