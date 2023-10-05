package com.backend.Desktop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    @JsonIgnore
    @OneToMany(mappedBy = "division")
    private Set<Class> classes;

    @JsonIgnore
    @OneToMany(mappedBy = "division")
    private Set<Student> students;
}
