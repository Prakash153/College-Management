package com.prakash.CollegeManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(nullable = false)
    private String name ;

    // many students can be taught by many professors
    @ManyToMany(mappedBy = "students")
    @JsonIgnore
    private Set<ProfessorEntity> professors ;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Subjects_Studied_by_Students",
    joinColumns = @JoinColumn(name = "StudentId"),
    inverseJoinColumns = @JoinColumn(name = "SubjectId"))

    private Set<SubjectEntity> SubjectsStudy;

    // Admission record Of STUDENT :

   @OneToOne(cascade = CascadeType.ALL)

     @JoinTable(name = "Student_Admission_Record",
     joinColumns = @JoinColumn(name="studentId" ),
     inverseJoinColumns = @JoinColumn(name = "enrollmentId"))
   AdmissionRecordEntity admissionRecord;


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StudentEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
