package com.backend.Desktop.Repository;

import com.backend.Desktop.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRespository extends JpaRepository<Note, Integer> {
}
