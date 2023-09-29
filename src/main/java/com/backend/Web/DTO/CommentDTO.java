package com.backend.Web.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Integer id;
    private String content;
    private LocalDate createTime;
    private String firstname;
    private String lastname;
    private String role;
}

