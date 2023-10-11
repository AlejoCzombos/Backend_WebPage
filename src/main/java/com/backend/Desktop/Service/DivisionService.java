package com.backend.Desktop.Service;

import com.backend.Desktop.DTO.ClassDTO;
import com.backend.Desktop.Entity.Class;
import com.backend.Desktop.Entity.Division;
import com.backend.Desktop.Entity.Student;
import com.backend.Desktop.Repository.ClassRepository;
import com.backend.Desktop.Repository.DivisionRepository;
import com.backend.Desktop.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DivisionService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);
    private final ModelMapper modelMapper;

    private final DivisionRepository divisionRepository;
    private final StudentRepository studentRepository;

    public ResponseEntity<Division> getById(Integer divisionId){
        Optional<Division> divisionOptional = divisionRepository.findById(divisionId);

        if (divisionOptional.isEmpty()){
            log.warn("Division no exist");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(divisionOptional.get());
    }

    public ResponseEntity<List<Student>> getstudentsByDivision(Integer divisionId){
        Optional<Division> divisionOptional = divisionRepository.findById(divisionId);

        if (divisionOptional.isEmpty()){
            log.warn("Division no exist");
            return ResponseEntity.notFound().build();
        }

        Division division = divisionOptional.get();



        return ResponseEntity.ok(division.getStudents());
    }

    public ResponseEntity<List<ClassDTO>> getClassesById(Integer divisionId){
        Optional<Division> divisionOptional = divisionRepository.findById(divisionId);

        if (divisionOptional.isEmpty()){
            log.warn("Division no exist");
            return ResponseEntity.notFound().build();
        }

        Division division = divisionOptional.get();
        List<Class> classes = division.getClasses();

        List<ClassDTO> classDTOS = classes.stream().map(aClass -> {
            ClassDTO classDTO = modelMapper.map(aClass, ClassDTO.class);
            classDTO.division_id = aClass.getDivision().getId();
            return classDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(classDTOS);
    }
    public ResponseEntity<Division> create(Division division){

        if (division.getId() != null){
            log.warn("trying to create a Division with id");
            return ResponseEntity.badRequest().build();
        }

        Division result = divisionRepository.save(division);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Division> linkStudents(Integer divisionId , String[] studentsIds){

        Optional<Division> divisionOptional = divisionRepository.findById(divisionId);

        if (divisionOptional.isEmpty()){
            log.warn("Trying to link students a non exist Division");
            return ResponseEntity.notFound().build();
        }

        Division division = divisionOptional.get();

        Integer[] studentsIdsInteger = Arrays.stream(studentsIds)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        for (Integer studentId : studentsIdsInteger) {

            Optional<Student> studentOptional = studentRepository.findById(studentId);

            if (studentOptional.isEmpty()){
                log.warn("Trying to link a non exist Student");
                return ResponseEntity.notFound().build();
            }
        }

        for (Integer studentId : studentsIdsInteger){

            Optional<Student> studentOptional = studentRepository.findById(studentId);

            Student student = studentOptional.get();

            division.getStudents().add(student);

            divisionRepository.save(division);
        }

        return ResponseEntity.ok(division);
    }


    public ResponseEntity<Division> deleteWithId(Integer divisionId){
        Optional<Division> divisionOptional = divisionRepository.findById(divisionId);

        if (divisionOptional.isEmpty()){
            log.warn("Division no exist");
            return ResponseEntity.notFound().build();
        }

        divisionRepository.deleteById(divisionId);

        return ResponseEntity.ok().build();
    }

}
