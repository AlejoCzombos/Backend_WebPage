package com.backend.Desktop.DTO;

import com.backend.Desktop.Entity.Student;
import lombok.Data;

@Data
public class NoteDTO {

    private Integer numeric_note;
    private Integer quarter;

    private Student student;

    private String class_name;
}
