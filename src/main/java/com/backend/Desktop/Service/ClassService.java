package com.backend.Desktop.Service;

import com.backend.Desktop.DTO.ClassDTO;
import com.backend.Desktop.DTO.StudentUserDTO;
import com.backend.Desktop.Entity.*;
import com.backend.Desktop.Entity.Class;
import com.backend.Desktop.Repository.*;
import com.backend.Login.User.User;
import com.backend.Login.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.core.userdetails.UserDetailsResourceFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);
    private final ModelMapper modelMapper;

    private final DivisionRepository divisionRepository;
    private final ClassRepository classRepository;
    private final ClassroomRepository classroomRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    public ResponseEntity<List<StudentUserDTO>> getStudentsByClass(Integer classId){

        Optional<Class> classOptional = classRepository.findById(classId);

        if (classOptional.isEmpty()){
            log.warn("Trying to delete a non exist class");
            return ResponseEntity.notFound().build();
        }

        Class aClass = classOptional.get();

        if (aClass.getDivision() == null) {
            log.warn("The class does not have a division");
            return ResponseEntity.badRequest().build();
        }

        Division division = aClass.getDivision();

        if (division.getStudents() == null) {
            log.warn("The division does not have a students");
            return ResponseEntity.badRequest().build();
        }

        List<Student> studentList = division.getStudents();

        List<StudentUserDTO> studentUserDTOS = studentList.stream().map(student -> {

            StudentUserDTO studentUserDTO = modelMapper.map(student, StudentUserDTO.class);

            User user = userRepository.findById(student.getId()).get();

            studentUserDTO.setDni(user.getDni());
            studentUserDTO.setUsername(user.getUsername());

            return studentUserDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(studentUserDTOS);
    }

    public ResponseEntity<Class> basicCreation(Class aClass){

        if (aClass.getId() != null){
            log.warn("trying to create a Class with id");
            return ResponseEntity.badRequest().build();
        }

        Class result = classRepository.save(aClass);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Class> completeCreation(Class aClass, Integer teacherId, Integer classRoomId, Integer divisionId){

        Optional<Classroom> classroomOptional = classroomRepository.findById(classRoomId);
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        Optional<Division> divisionOptional = divisionRepository.findById(divisionId);

        if (aClass.getId() != null){
            log.warn("trying to create a student with id");
            return ResponseEntity.badRequest().build();
        } else if (teacherOptional.isEmpty()) {
            log.warn("Teacher no exist");
            return ResponseEntity.notFound().build();
        }else if (classroomOptional.isEmpty()){
            log.warn("Classroom no exist");
            return ResponseEntity.notFound().build();
        }else if (divisionOptional.isEmpty()){
            log.warn("Division no exist");
            return ResponseEntity.notFound().build();
        }

        Classroom classroom = classroomOptional.get();
        Teacher teacher = teacherOptional.get();
        Division division = divisionOptional.get();

        aClass.setClassroom(classroom);
        aClass.setTeacher(teacher);
        aClass.setDivision(division);

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
