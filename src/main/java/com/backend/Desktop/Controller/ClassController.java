package com.backend.Desktop.Controller;

import com.backend.Desktop.Entity.Class;
import com.backend.Desktop.Entity.Schedule;
import com.backend.Desktop.Entity.Student;
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

    @GetMapping("/{classId}/students")
    ResponseEntity<List<Student>> getStudentsByClass(@PathVariable Integer classId){
        return classService.getStudentsByClass(classId);
    }

    @GetMapping
    public List<Class> getAll(){
        return classRepository.findAll();
    }

    @PostMapping("/schedule/{classId}")
    public ResponseEntity<Schedule> createSchedule(@PathVariable Integer classId, @RequestBody Schedule schedule){
        return classService.createSchedule(classId, schedule);
    }

    @PostMapping
    public ResponseEntity<Class> basicCreation(@RequestBody Class aClass){
        return classService.basicCreation(aClass);
    }

    @PostMapping("/{teacherId}/{classroomId}/{divisionId}")
    public ResponseEntity<Class> completeCreation(
            @RequestBody Class aClass,
            @PathVariable Integer teacherId,
            @PathVariable Integer classroomId,
            @PathVariable Integer divisionId
    ){
        return classService.completeCreation(aClass, teacherId, classroomId, divisionId);
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<Class> deleteWithId(@PathVariable Integer classId){
        return classService.deleteWithId(classId);
    }
}