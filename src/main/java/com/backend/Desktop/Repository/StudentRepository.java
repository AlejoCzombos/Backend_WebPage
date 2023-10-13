package com.backend.Desktop.Repository;


import com.backend.Desktop.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Integer> {
    List<Student> findAllByOrderByLastnameAsc();

}
