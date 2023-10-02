package com.backend.Desktop.Service;

import com.backend.Desktop.Entity.Class;
import com.backend.Desktop.Entity.Teacher;
import com.backend.Desktop.Repository.ClassRepository;
import com.backend.Desktop.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final TeacherRepository teacherRepository;
    private final ClassRepository classRepository;

    public ResponseEntity<Teacher> create(Teacher teacher){

        if (teacher.getId() != null){
            log.warn("trying to create a student with id");
            return ResponseEntity.badRequest().build();
        }

        Teacher result = teacherRepository.save(teacher);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Teacher> addClass(Integer classId){

        Optional<Class> classOptional = classRepository.findById(classId);

        if (classOptional.isEmpty()){
            log.warn("Trying to add a non exist class");
        }

        //TODO: Completar esta funcionalidad
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Teacher> deleteWithId(Integer teacherId){
        Optional<Teacher> classOptional = teacherRepository.findById(teacherId);

        if (classOptional.isEmpty()){
            log.warn("Trying to delete a non exist class");
            return ResponseEntity.notFound().build();
        }

        teacherRepository.deleteById(teacherId);
        return ResponseEntity.ok().build();
    }

}
