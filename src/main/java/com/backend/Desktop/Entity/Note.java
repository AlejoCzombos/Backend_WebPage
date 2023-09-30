package com.backend.Desktop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "student_id")
    @OneToOne
    private Student student;

    @JoinColumn(name = "teacher_id")
    @OneToOne
    private Teacher teacher;

    @JoinColumn(name = "class_id")
    @OneToOne
    private Class aClass;

    private Integer numeric_note;
    private Integer quarter;
}
