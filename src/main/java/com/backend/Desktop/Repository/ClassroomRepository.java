package com.backend.Desktop.Repository;

import com.backend.Desktop.Entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository  extends JpaRepository<Classroom, Integer> {
}
