package com.backend.Desktop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    private Integer id;

    private String file_number;

    private String firstname;
    private String lastname;

    @OneToOne(mappedBy = "student")
    private Note note;

    @ManyToMany(mappedBy = "students")
    private List<Parent> parents;

    @ManyToMany(mappedBy = "students")
    private List<Class> classes;

    public Student(String firstname, String lastname) {
        Random random = new Random();

        Integer randomNumber = random.nextInt() * 10 + 1000;

        if (randomNumber < 0) randomNumber *= -1;

        this.file_number = String.valueOf(randomNumber);
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
