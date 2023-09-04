package com.backend.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    Integer id;
    String titule;
    String content;
    String firstname;
    String lastname;
    LocalDate createTime;
    List<CommentDTO> comments;

}
