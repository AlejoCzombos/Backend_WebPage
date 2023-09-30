package com.backend.Desktop.Repository;

import com.backend.Desktop.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository  extends JpaRepository<Teacher, Integer> {
}
