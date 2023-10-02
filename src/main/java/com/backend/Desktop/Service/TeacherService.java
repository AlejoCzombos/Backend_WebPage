package com.backend.Desktop.Service;

import com.backend.Desktop.Entity.Class;
import com.backend.Desktop.Entity.Student;
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

    public ResponseEntity<Teacher> getById(Integer id){

        Optional<Teacher> teacherOptional = teacherRepository.findById(id);

        if (teacherOptional.isEmpty()){
            log.warn("Teacher is not found");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(teacherOptional.get());
    }

    public ResponseEntity<Teacher> create(Teacher teacher){

        if (teacher.getId() != null){
            log.warn("trying to create a student with id");
            return ResponseEntity.badRequest().build();
        }

        Teacher result = teacherRepository.save(teacher);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Teacher> addClass(Integer teacherId,Integer classId){

        Optional<Class> classOptional = classRepository.findById(classId);
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);

        if (classOptional.isEmpty()){
            log.warn("Trying to add teacher a non exist class");
        }else if (teacherOptional.isEmpty()){
            log.warn("Trying to add class a non exist teacher");
        }

        Teacher teacher = teacherOptional.get();
        Class aClass = classOptional.get();

        teacher.getClasses().add(aClass);
        aClass.setTeacher(teacher);

        return ResponseEntity.ok(teacher);
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
