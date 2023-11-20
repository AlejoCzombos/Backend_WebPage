package com.backend.Desktop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String class_name;
    private String english_class_name;

    @Enumerated(EnumType.STRING)
    private Education education;

    private String year;

    @ManyToOne()
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @ManyToMany
    @JoinTable(
            name = "class_teacher",
            joinColumns = @JoinColumn(name = "class_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    )
    private List<Teacher> teachers = new ArrayList<Teacher>();

    @ManyToOne()
    @JoinColumn(name = "division_id")
    private Division division;

    @OneToMany(mappedBy = "aClass")
    private List<Schedule> schedules;
}
