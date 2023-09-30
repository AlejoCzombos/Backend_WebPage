package com.backend.Desktop.Repository;


import com.backend.Desktop.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository  extends JpaRepository<Student, Integer> {

    List<Student> findAllByOrderByLastnameAsc();
}
