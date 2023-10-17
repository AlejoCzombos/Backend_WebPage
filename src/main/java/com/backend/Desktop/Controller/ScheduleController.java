package com.backend.Desktop.Controller;

import com.backend.Desktop.Entity.Schedule;
import com.backend.Desktop.Service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/{classId}")
    public ResponseEntity<Schedule> createSchedule(@PathVariable Integer classId, @RequestBody Schedule schedule){
        return scheduleService.createSchedule(classId, schedule);
    }

}
