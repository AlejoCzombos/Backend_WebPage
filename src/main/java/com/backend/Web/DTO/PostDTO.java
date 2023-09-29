package com.backend.Web.DTO;

import com.backend.Login.User.Role;
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
    Role role;
    LocalDate createTime;
    List<CommentDTO> comments;

}
