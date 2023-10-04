package com.backend.Desktop.Service;

import com.backend.Desktop.Entity.Class;
import com.backend.Desktop.Entity.Classroom;
import com.backend.Desktop.Entity.Parent;
import com.backend.Desktop.Repository.ClassRepository;
import com.backend.Desktop.Repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final ClassroomRepository classroomRepository;
    private final ClassRepository classRepository;

    public ResponseEntity<Classroom> findById(Integer classroomId){
        Optional<Classroom> classroomOptional = classroomRepository.findById(classroomId);

        if (classroomOptional.isEmpty()){
            log.warn("Classroom is not found");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(classroomOptional.get());
    }

    public ResponseEntity<Classroom> create(Classroom classroom){

        if (classroom.getId() != null){
            log.warn("Trying to create a classroom with id");
            return ResponseEntity.badRequest().build();
        }

        Classroom result = classroomRepository.save(classroom);

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Classroom> linkClass(Integer classroomId, Integer classId){

        Optional<Class> classOptional = classRepository.findById(classId);
        Optional<Classroom> classroomOptional = classroomRepository.findById(classroomId);

        if (classOptional.isEmpty()){
            log.warn("Trying to link a non exist class");
            return ResponseEntity.notFound().build();
        }else if(classroomOptional.isEmpty()){
            log.warn("Trying to link a non exist classroom");
            return ResponseEntity.notFound().build();
        }

        Classroom classroom = classroomOptional.get();
        Class aClass = classOptional.get();

        classroom.getClasses().add(aClass);

        Classroom result = classroomRepository.save(classroom);

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Classroom> deleteWithId(Integer classroomId){
        Optional<Classroom> classroomOptional = classroomRepository.findById(classroomId);

        if (classroomOptional.isEmpty()){
            log.warn("Trying to delete a non exist class");
            return ResponseEntity.notFound().build();
        }

        classroomRepository.deleteById(classroomId);
        return ResponseEntity.ok().build();
    }

}
