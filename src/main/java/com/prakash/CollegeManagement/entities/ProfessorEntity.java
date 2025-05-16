package com.prakash.CollegeManagement.entities;

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
@Table(name = "professor")
public class ProfessorEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(nullable = false)
    private String name ;


    // many professors can teach many students
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "professors_students",
    joinColumns = @JoinColumn(name = "professorId"),
    inverseJoinColumns = @JoinColumn(name = "studentId"))
    private Set<StudentEntity> students;


    @OneToMany(mappedBy = "professorAssigned" , fetch = FetchType.LAZY)
    private Set<SubjectEntity> subjects ;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProfessorEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
