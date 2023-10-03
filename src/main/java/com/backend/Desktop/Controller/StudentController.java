package com.backend.Desktop.Controller;

import com.backend.Desktop.Entity.Student;
import com.backend.Desktop.Repository.StudentRepository;
import com.backend.Desktop.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @GetMapping()
    public List<Student> getAll(){
        return studentRepository.findAllByOrderByLastnameAsc();
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getById(@PathVariable Integer studentId){
        return studentService.getById(studentId);
    }

    @PostMapping("/{studentId}/{classId}")
    public ResponseEntity<Student> linkClass(@PathVariable Integer studentId, @PathVariable Integer classId){
        return studentService.linkClass(studentId, classId);
    }

    @PostMapping()
    public ResponseEntity<Student> create(@RequestBody Student student){
        return studentService.create(student);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Student> delete(@PathVariable Integer studentId){
        return studentService.deleteById(studentId);
    }

}
