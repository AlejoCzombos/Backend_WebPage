package com.backend.Desktop.Service;

import com.backend.Desktop.Entity.Class;
import com.backend.Desktop.Entity.Student;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

public class DivisionService {

    /*
    public ResponseEntity<Class> linkStudents(Integer classId , String[] studentsIds){

        Optional<Class> classOptional = classRepository.findById(classId);

        if (classOptional.isEmpty()){
            log.warn("Trying to add students a non exist Class");
            return ResponseEntity.notFound().build();
        }

        Class aClass = classOptional.get();

        Integer[] studentsIdsInteger = Arrays.stream(studentsIds)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        for (Integer studentId : studentsIdsInteger) {

            Optional<Student> studentOptional = studentRepository.findById(studentId);

            if (studentOptional.isEmpty()){
                log.warn("Trying to add a non exist Student");
                return ResponseEntity.notFound().build();
            }
        }

        for (Integer studentId : studentsIdsInteger){

            Optional<Student> studentOptional = studentRepository.findById(studentId);

            Student student = studentOptional.get();

            aClass.getStudents().add(student);

            classRepository.save(aClass);
        }

        return ResponseEntity.ok(aClass);
    }
    */

}
