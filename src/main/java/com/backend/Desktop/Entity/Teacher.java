package com.backend.Desktop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teacher")
public class Teacher {

    @Id
    private Integer id;

    @JsonIgnore
    @ManyToMany(mappedBy = "teachers")
    private List<Class> classes;

    private String firstname;
    private String lastname;

    public Teacher(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
