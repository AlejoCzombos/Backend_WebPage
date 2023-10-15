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
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String class_name;

    @Enumerated(EnumType.STRING)
    private Education education;

    private String year;

    @ManyToOne()
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @ManyToOne()
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne()
    @JoinColumn(name = "division_id")
    private Division division;

    @OneToMany(mappedBy = "aClass")
    private List<Schedule> schedules;
}
