package com.backend.Desktop.Controller;

import com.backend.Desktop.Entity.Classroom;
import com.backend.Desktop.Repository.ClassroomRepository;
import com.backend.Desktop.Service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    private final ClassroomRepository classroomRepository;
    private final ClassroomService classroomService;

    @GetMapping("/{classroomId}")
    public ResponseEntity<Classroom> getById(@PathVariable Integer classroomId){
        return classroomService.findById(classroomId);
    }

    @GetMapping
    public List<Classroom> getAll(){
        return classroomRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Classroom> create(@RequestBody Classroom classroom){
        return classroomService.create(classroom);
    }

    @PutMapping("/{classroomId}/{classId}")
    public ResponseEntity<Classroom> linkClass(@PathVariable Integer classroomId, @PathVariable Integer classId){
        return classroomService.linkClass(classroomId, classId);
    }

    @DeleteMapping("/{classroomId}")
    public ResponseEntity<Classroom> deleteWithId(@PathVariable Integer classroomId){
        return classroomService.deleteWithId(classroomId);
    }
}
