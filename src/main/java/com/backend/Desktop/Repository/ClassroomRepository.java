package com.backend.Desktop.Repository;

import com.backend.Desktop.Entity.Class;
import com.backend.Desktop.Entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository  extends JpaRepository<Classroom, Integer> {
}
