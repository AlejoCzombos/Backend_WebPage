package com.backend.Desktop.Service;

import com.backend.Desktop.Entity.Class;
import com.backend.Desktop.Entity.Schedule;
import com.backend.Desktop.Repository.ClassRepository;
import com.backend.Desktop.Repository.ScheduleRespository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final Logger log = LoggerFactory.getLogger(ScheduleService.class);

    private final ScheduleRespository scheduleRespository;
    private final ClassRepository classRepository;

    public ResponseEntity<Schedule> createSchedule(Integer classId, Schedule schedule){

        Optional<Class> classOptional = classRepository.findById(classId);

        if (schedule.getAClass() != null){
            log.warn("trying to create a Schedule with id");
            return ResponseEntity.badRequest().build();

        } else if (classOptional.isEmpty()) {
            log.warn("Class non exist");
            return ResponseEntity.badRequest().build();
        }

        schedule.setAClass(classOptional.get());

        Schedule result = scheduleRespository.save(schedule);
        return ResponseEntity.ok(result);
    }

}
