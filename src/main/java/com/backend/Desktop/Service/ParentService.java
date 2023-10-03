package com.backend.Desktop.Service;

import com.backend.Desktop.Entity.Parent;
import com.backend.Desktop.Entity.Student;
import com.backend.Desktop.Repository.ParentRepository;
import com.backend.Desktop.Repository.StudentRepository;
import com.backend.Login.User.User;
import com.backend.Login.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);
    private final UserRepository userRepository;

    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;

    public ResponseEntity<Parent> getById(Integer id){

        Optional<Parent> parentOptional = parentRepository.findById(id);

        if (parentOptional.isEmpty()){
            log.warn("Parent is not found");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(parentOptional.get());
    }

    public void createManually(Parent parent, String username){
        Optional<User> userOptional = userRepository.findByUsername(username);

        parent.setId(userOptional.get().getId());
        parentRepository.save(parent);
    }

    public ResponseEntity<Parent> create(Parent parent){

        if (parent.getId() != null){
            log.warn("trying to create a parent with id");
            return ResponseEntity.badRequest().build();
        }

        Parent result = parentRepository.save(parent);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Parent> linkChildren(Integer parentId, Integer studentId){

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Parent> parentOptional = parentRepository.findById(parentId);

        if (studentOptional.isEmpty() || parentOptional.isEmpty()){
            log.warn("Student or Parent is not found");
            return ResponseEntity.notFound().build();
        }

        Student student = studentOptional.get();
        Parent parent = parentOptional.get();

        parent.getChildrens().add(student);

        Parent result = parentRepository.save(parent);

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Parent> deleteById(Integer id){
        Optional<Parent> parentOptional = parentRepository.findById(id);

        if (parentOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        parentRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
