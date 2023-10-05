package com.backend.Desktop.Repository;

import com.backend.Desktop.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRespository extends JpaRepository<Schedule, Integer> {
}
