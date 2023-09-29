package com.backend.Web.Repository;

import com.backend.Web.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByPostId(Integer post_id);
    List<Comment> findAllById(Integer post_id);
}
