package com.backend.Desktop.Repository;

import com.backend.Desktop.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRespository extends JpaRepository<Note, Integer> {

    List<Note> findAllByStudentId(Integer student_id);

}
