package com.backend.Desktop.Service;

import com.backend.Desktop.Entity.*;
import com.backend.Desktop.Repository.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeeService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final FeeRepository feeRepository;
    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;
    private final MonthlyFeeRepostory monthlyFeeRepostory;

    public ResponseEntity<Fee> getById(Integer feeId){
        Optional<Fee> feeOptional = feeRepository.findById(feeId);

        if (feeOptional.isEmpty()){
            log.warn("Fee no exist");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(feeOptional.get());
    }

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

        fee.setMonthlyFees(createMonthlyFees(result));

        return ResponseEntity.ok(result);
    }

    public Fee manallyCreation(Fee fee){
        Fee result = feeRepository.save(fee);

        fee.setMonthlyFees(createMonthlyFees(result));

        Fee finalResult = feeRepository.save(fee);

        return finalResult;
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

    private List<MonthlyFee> createMonthlyFees(Fee fee){

        List<MonthlyFee> monthlyFees = new ArrayList<MonthlyFee>();

        Calendar now = Calendar.getInstance();
        int actualMonth = (now.get(Calendar.MONTH) + 1);

        for (int i = 1; i <= actualMonth; i++){
            boolean paid = true;

            if (i == actualMonth && Math.random() < 0.2){
                paid = false;
            }

            MonthlyFee monthlyFee = new MonthlyFee(fee, Month.of(i), paid);
            MonthlyFee result = monthlyFeeRepostory.save(monthlyFee);

            monthlyFees.add(result);
        }
        return monthlyFees;
    }

}
