package com.backend.Desktop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String file_number;

    private String firstname;
    private String lastname;

    @ManyToMany(mappedBy = "students")
    private List<Parent> parents;

    @ManyToMany(mappedBy = "students")
    private List<Class> classes;
}
