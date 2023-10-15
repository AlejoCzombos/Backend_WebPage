package com.backend.Desktop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "division")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String division_name;

    private String year;

    @Enumerated(EnumType.STRING)
    private Education education;

    @JsonIgnore
    @OneToMany(mappedBy = "division")
    private List<Class> classes;

    @JsonIgnore
    @OneToMany(mappedBy = "division")
    private List<Student> students;
}
