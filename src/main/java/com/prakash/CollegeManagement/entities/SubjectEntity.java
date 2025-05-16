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
@Table(name = "subject")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(nullable = false)
    private String title ;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Subjects_Under_Professor")
    @JsonIgnore
    private ProfessorEntity professorAssigned ;


    @ManyToMany(mappedBy = "SubjectsStudy")
    @JsonIgnore
    private Set<StudentEntity> studentSubject;


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SubjectEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }

}
