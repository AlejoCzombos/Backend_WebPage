package com.backend.Desktop.DTO;

import com.backend.Desktop.Entity.Student;
import lombok.Data;

@Data
public class NoteDTO {

    private String class_name;

    private Integer numeric_note_1;
    private Integer numeric_note_2;
    private Integer numeric_note_3;
}
