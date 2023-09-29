package com.backend.Web.Entity;

import com.backend.Login.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    public String titule;

    @Column(length = 1000)
    public
    String content;
    @CreatedDate
    public
    LocalDate createTime;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    public
    List<Comment> comments;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    User user;

}
