package com.backend.Desktop.Service;

import com.backend.Desktop.Entity.Student;
import com.backend.Desktop.Repository.StudentRepository;
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

    private static StudentRepository studentRepository;

    public ResponseEntity<Student> getById(Integer id){
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentOptional.get());
    }

    public ResponseEntity<Student> create(Student student){

        if (student.getId() != null){
            log.warn("trying to create a student with id");
            return ResponseEntity.badRequest().build();
        }

        Student result = studentRepository.save(student);
        return ResponseEntity.ok(result);
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