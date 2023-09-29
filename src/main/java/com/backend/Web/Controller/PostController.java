package com.backend.Web.Controller;

import com.backend.Login.User.User;
import com.backend.Login.User.UserRepository;
import com.backend.Web.Entity.Comment;
import com.backend.Web.DTO.CommentDTO;
import com.backend.Web.DTO.PostDTO;
import com.backend.Web.Entity.Post;
import com.backend.Web.Repository.CommentRepository;
import com.backend.Web.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final Logger log = LoggerFactory.getLogger(PostController.class);

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @GetMapping("/posts")
    public List<PostDTO> findAllWithComments(){
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOs = new ArrayList<>();

        for (Post post : posts) {
            List<Comment> comments = post.getComments();
            List<CommentDTO> commentDTOs = new ArrayList<>();

            for (Comment comment : comments) {
                User user = comment.getUser();
                CommentDTO commentDTO = new CommentDTO(
                        comment.getId(),
                        comment.getContent(),
                        comment.getCreateTime(),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getRole().toString()
                );
                commentDTOs.add(commentDTO);
            }

            PostDTO postDTO = new PostDTO(
                    post.getId(),
                    post.getTitule(),
                    post.getContent(),
                    post.getUser().getFirstname(),
                    post.getUser().getLastname(),
                    post.getUser().getRole(),
                    post.getCreateTime(),
                    commentDTOs
            );
            postDTOs.add(postDTO);
        }
        return postDTOs;
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> findAllByPostId(@PathVariable Integer postId){
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        List<CommentDTO> commentDTOS = new ArrayList<>();

        for (Comment comment : comments) {
            User user = comment.getUser();
            CommentDTO commentDTO = new CommentDTO(
                    comment.getId(),
                    comment.getContent(),
                    comment.getCreateTime(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getRole().toString()
            );
            commentDTOS.add(commentDTO);
        }

        return commentDTOS;
    }

    @PostMapping("/posts/{userId}")
    public ResponseEntity<Post> create( @PathVariable Integer userId ,@RequestBody Post post){

        Optional<User> user = userRepository.findById(userId);

        if (post.getId() != null || user.isEmpty()){
            log.warn("trying to create a post with id");
            return ResponseEntity.badRequest().build();
        }

        post.setUser(user.get());
        post.setCreateTime(LocalDate.now());

        Post result = postRepository.save(post);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/posts")
    public ResponseEntity<Post> update(@RequestBody Post post){

        if (post.getId() == null){
            log.warn("trying to update post wihout id");
            return ResponseEntity.badRequest().build();
        }

        Optional<Post> optionalPost = postRepository.findById(post.getId());

        if (optionalPost.isEmpty()){
            log.warn("trying to update a non existent book");
            return ResponseEntity.noContent().build();
        }

        Post updatedPost = optionalPost.get();

        updatedPost.comments = post.comments;
        updatedPost.content = post.content;
        updatedPost.titule = post.titule;
        updatedPost.createTime = LocalDate.now();

        postRepository.save(updatedPost);

        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Post> delete(@PathVariable Integer id){

        postRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
