package com.backend.Desktop.Service;

import com.backend.Desktop.Entity.Class;
import com.backend.Desktop.Entity.Parent;
import com.backend.Desktop.Entity.Student;
import com.backend.Desktop.Repository.ClassRepository;
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
public class StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);
    private final UserRepository userRepository;

    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final ClassRepository classRepository;

    public ResponseEntity<Student> getById(Integer id){

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isEmpty()){
            log.warn("Student is not found");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentOptional.get());
    }

    public void createManually(Student student, String username){
        Optional<User> userOptional = userRepository.findByUsername(username);

        student.setId(userOptional.get().getId());
        studentRepository.save(student);
    }

    public ResponseEntity<Student> create(Student student){

        if (student.getId() != null){
            log.warn("trying to create a student with id");
            return ResponseEntity.badRequest().build();
        }

        Student result = studentRepository.save(student);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Student> addParent(Integer studentId, Integer parentId){

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Parent> parentOptional = parentRepository.findById(parentId);

        if (studentOptional.isEmpty() || parentOptional.isEmpty()){
            log.warn("Student or Parent is not found");
            return ResponseEntity.notFound().build();
        }

        Student student = studentOptional.get();
        Parent parent = parentOptional.get();

        student.getParents().add(parent);

        //TODO: Ver si se guarda automaticamente
        return ResponseEntity.ok(student);
    }

    public ResponseEntity<Student> addClass(Integer studentId, Integer classId){

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Class> classOptional =  classRepository.findById(classId);

        if (studentOptional.isEmpty() || classOptional.isEmpty()){
            log.warn("Student or Class is not found");
            return ResponseEntity.notFound().build();
        }

        Student student = studentOptional.get();
        Class aClass = classOptional.get();

        student.getClasses().add(aClass);

        //TODO: Ver si se guarda automaticamente
        return ResponseEntity.ok(student);
    }

    public ResponseEntity<Student> deleteById(Integer id){
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        studentRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }


}