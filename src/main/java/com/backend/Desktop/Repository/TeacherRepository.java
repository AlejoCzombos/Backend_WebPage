package com.backend.Desktop.Repository;

import com.backend.Desktop.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository  extends JpaRepository<Teacher, Integer> {
}
