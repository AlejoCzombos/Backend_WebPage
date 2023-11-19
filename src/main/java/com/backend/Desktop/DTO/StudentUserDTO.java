package com.backend.Desktop.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StudentUserDTO {

    private String firstname;
    private String lastname;
    private String file_number;
    private String dni;
    private String username;

    public StudentUserDTO(String firstname, String lastname, String file_number, String dni, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.file_number = file_number;
        this.dni = dni;
        this.username = username;
    }

}
