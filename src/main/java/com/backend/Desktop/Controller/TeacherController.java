package com.backend.Desktop.Controller;

import com.backend.Desktop.Entity.Teacher;
import com.backend.Desktop.Repository.TeacherRepository;
import com.backend.Desktop.Service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherRepository teacherRepository;

    @GetMapping
    public List<Teacher> listAll(){
        return teacherRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Teacher> create(@RequestBody Teacher teacher){
        return teacherService.create(teacher);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> getById(@PathVariable Integer teacherId){
        return getById(teacherId);
    }

    @PostMapping("/{teacherId}/{classId}")
    public ResponseEntity<Teacher> addClass(@PathVariable Integer teacherId, @PathVariable Integer classId){
        return teacherService.addClass(teacherId, classId);
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Teacher> deleteWithId(@PathVariable Integer teacherId){
        return teacherService.getById(teacherId);
    }

}
