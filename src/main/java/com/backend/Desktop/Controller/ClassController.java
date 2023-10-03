package com.backend.Desktop.Controller;

import com.backend.Desktop.Entity.Class;
import com.backend.Desktop.Repository.ClassRepository;
import com.backend.Desktop.Service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/classes")
public class ClassController {

    private final ClassService classService;
    private final ClassRepository classRepository;

    @GetMapping
    public List<Class> getAll(){
        return classRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Class> basicCreation(@RequestBody Class aClass){
        return classService.basicCreation(aClass);
    }

    @PostMapping("/{teacherId}/{classroomId}")
    public ResponseEntity<Class> completeCreation(
            @RequestBody Class aClass,
            @PathVariable Integer teacherId,
            @PathVariable Integer classroomId
    ){
        return classService.completeCreation(aClass, teacherId, classroomId);
    }

    @PostMapping("/{classId}/{studentsId}")
    public ResponseEntity<Class> addStudents(@PathVariable Integer classId, @PathVariable String[] studentsIds){
        return classService.linkStudents(classId, studentsIds);
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<Class> deleteWithId(@PathVariable Integer classId){
        return classService.deleteWithId(classId);
    }
}
