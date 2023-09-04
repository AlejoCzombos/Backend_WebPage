package com.backend.Post;

import com.backend.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String titule;

    @Column(length = 1000)
    String content;
    @CreatedDate
    LocalDate createTime;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    List<Comment> comments;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    User user;

}
