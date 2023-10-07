package com.backend.Web.Controller;

import com.backend.Login.User.Role;
import com.backend.Login.User.User;
import com.backend.Login.User.UserRepository;
import com.backend.Web.Entity.Comment;
import com.backend.Web.Entity.Post;
import com.backend.Web.Repository.CommentRepository;
import com.backend.Web.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final Logger log = LoggerFactory.getLogger(PostController.class);

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @GetMapping("/role/{userId}")
    public ResponseEntity<Role> getRole(@PathVariable Integer userId){
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();

        return ResponseEntity.ok(user.getRole());
    }

    @GetMapping("/posts/users")
    public List<User> GetUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/comments/{postId}/{userId}")
    public ResponseEntity<Comment> createComment(
            @PathVariable Integer postId,
            @PathVariable Integer userId,
            @RequestBody Comment comment
    ) {
        Optional<Post> optPost = postRepository.findById(postId);
        Optional<User> optUser = userRepository.findById(userId);

        if (optPost.isEmpty()) {
            log.warn("Post not found with id: " + postId);
            return ResponseEntity.notFound().build();
        }

        if (optUser.isEmpty()) {
            log.warn("User not found with id: " + userId);
            return ResponseEntity.notFound().build();
        }

        if (comment.getId() != null) {
            log.warn("Trying to create a comment with an existing id");
            return ResponseEntity.badRequest().build();
        }

        comment.setCreateTime(LocalDate.now());
        comment.setPost(optPost.get());
        comment.setUser(optUser.get());

        Comment result = commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @PutMapping("/comments")
    public ResponseEntity<Comment> update(@RequestBody Comment comment){

        if (comment.getId() == null){
            log.warn("trying to update comment wihout id");
            return ResponseEntity.badRequest().build();
        }

        Optional<Comment> optionalComment = commentRepository.findById(comment.getId());

        if (optionalComment.isEmpty()){
            log.warn("trying to update a non existent comment");
            return ResponseEntity.noContent().build();
        }

        Comment updatedComment = optionalComment.get();

        updatedComment.content = comment.content;
        updatedComment.createTime = LocalDate.now();

        commentRepository.save(updatedComment);

        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Comment> delete(@PathVariable Integer id){
        if(!commentRepository.existsById(id)){
            log.warn("trying to delete a non exist comment");
            return ResponseEntity.badRequest().build();
        }

        commentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
