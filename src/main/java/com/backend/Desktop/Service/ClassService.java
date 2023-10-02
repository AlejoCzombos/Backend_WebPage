package com.backend.Desktop.Service;

import com.backend.Desktop.Entity.Class;
import com.backend.Desktop.Entity.Classroom;
import com.backend.Desktop.Entity.Teacher;
import com.backend.Desktop.Repository.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final ClassRepository classRepository;
    private final ClassroomRepository classroomRepository;
    private final TeacherRepository teacherRepository;

    public ResponseEntity<Class> basicCreation(Class aClass){

        if (aClass.getId() != null){
            log.warn("trying to create a student with id");
            return ResponseEntity.badRequest().build();
        }

        Class result = classRepository.save(aClass);
        return ResponseEntity.ok(result);
    }
    public ResponseEntity<Class> completeCreation(Class aClass, Integer teacherId, Integer classRoomId){

        Optional<Classroom> classroomOptional = classroomRepository.findById(classRoomId);
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);

        if (aClass.getId() != null){
            log.warn("trying to create a student with id");
            return ResponseEntity.badRequest().build();
        } else if (teacherOptional.isEmpty()) {
            log.warn("Teacher no exist");
        }else if (classroomOptional.isEmpty()){
            log.warn("Classroom no exist");
        }

        Classroom classroom = classroomOptional.get();
        Teacher teacher = teacherOptional.get();

        aClass.setClassroom(classroom);
        aClass.setTeacher(teacher);

        Class result = classRepository.save(aClass);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Class> deleteWithId(Integer classId){
        Optional<Class> classOptional = classRepository.findById(classId);

        if (classOptional.isEmpty()){
            log.warn("Trying to delete a non exist class");
            return ResponseEntity.notFound().build();
        }

        classRepository.deleteById(classId);
        return ResponseEntity.ok().build();
    }

}
