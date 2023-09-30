package com.backend.Desktop.Entity;

import com.backend.Login.User.User;
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

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

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

        this.file_number = String.valueOf(random.nextInt() * 1000 + 1000);
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
