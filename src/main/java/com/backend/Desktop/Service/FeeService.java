package com.backend.Desktop.Service;

import com.backend.Desktop.Entity.*;
import com.backend.Desktop.Entity.Class;
import com.backend.Desktop.Repository.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeeService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final FeeRepository feeRepository;
    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;

    public ResponseEntity<Fee> basicCreation(Fee fee){

        if (fee.getId() != null){
            log.warn("trying to create a fee with id");
            return ResponseEntity.badRequest().build();
        }

        Fee result = feeRepository.save(fee);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Fee> completeCreation(Fee fee, Integer parentId, Integer studentId){

        Optional<Parent> parentOptional = parentRepository.findById(parentId);
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (fee.getId() != null){
            log.warn("trying to create a fee with id");
            return ResponseEntity.badRequest().build();

        } else if (parentOptional.isEmpty()) {
            log.warn("Parent no exist");
            return ResponseEntity.notFound().build();

        }else if (studentOptional.isEmpty()){
            log.warn("Student no exist");
            return ResponseEntity.notFound().build();
        }

        Parent parent = parentOptional.get();
        Student student = studentOptional.get();

        fee.setParent(parent);
        fee.setStudent(student);

        Fee result = feeRepository.save(fee);

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Fee> deleteWithId(Integer feeId){
        Optional<Fee> feeOptional = feeRepository.findById(feeId);

        if (feeOptional.isEmpty()){
            log.warn("Fee no exist");
            return ResponseEntity.notFound().build();
        }

        feeRepository.deleteById(feeId);
        return ResponseEntity.ok().build();
    }

}
