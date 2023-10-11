package com.backend.Desktop.Service;

import com.backend.Desktop.Entity.*;
import com.backend.Desktop.Entity.Class;
import com.backend.Desktop.Repository.ClassRepository;
import com.backend.Desktop.Repository.NoteRespository;
import com.backend.Desktop.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NoteService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final NoteRespository noteRespository;
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;

    public ResponseEntity<Note> basicCreation(Note note){

        if (note.getId() != null){
            log.warn("trying to create a note with id");
            return ResponseEntity.badRequest().build();
        }

        Note result = noteRespository.save(note);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Note> completeCreation(Note note, Integer classId, Integer studentId){

        Optional<Class> classOptional = classRepository.findById(classId);
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (note.getId() != null){
            log.warn("trying to create a note with id");
            return ResponseEntity.badRequest().build();

        } else if (classOptional.isEmpty()) {
            log.warn("Class no exist");
            return ResponseEntity.notFound().build();

        }else if (studentOptional.isEmpty()){
            log.warn("Student no exist");
            return ResponseEntity.notFound().build();
        }

        Class aClass = classOptional.get();
        Student student = studentOptional.get();

        note.setAClass(aClass);
        note.setStudent(student);

        Note result = noteRespository.save(note);

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Note> linkStudentAndClass(Integer noteId, Integer classId, Integer studentId){

        Optional<Note> noteOptional = noteRespository.findById(noteId);
        Optional<Class> classOptional = classRepository.findById(classId);
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (noteOptional.isEmpty()){
            log.warn("Note no exist");
            return ResponseEntity.notFound().build();

        } else if (classOptional.isEmpty()) {
            log.warn("Class no exist");
            return ResponseEntity.notFound().build();

        }else if (studentOptional.isEmpty()){
            log.warn("Student no exist");
            return ResponseEntity.notFound().build();
        }

        Class aClass = classOptional.get();
        Student student = studentOptional.get();
        Note note = noteOptional.get();

        note.setAClass(aClass);
        note.setStudent(student);

        Note result = noteRespository.save(note);

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Note> deleteWithId(Integer noteId){
        Optional<Note> noteOptional = noteRespository.findById(noteId);

        if (noteOptional.isEmpty()){
            log.warn("Note no exist");
            return ResponseEntity.notFound().build();
        }

        noteRespository.deleteById(noteId);
        return ResponseEntity.ok().build();
    }

    public List<Note> createNotes(Student student){

        Division division = student.getDivision();
        List<Class> classes = division.getClasses();

        List<Note> notes = new ArrayList<Note>();

        for (Class aClass : classes){
            Note note = new Note(student, aClass);

            Note result = noteRespository.save(note);

            notes.add(result);
        }
        return notes;
    }

}
