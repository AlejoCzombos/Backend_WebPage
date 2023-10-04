package com.backend.Desktop.Controller;

import com.backend.Desktop.Entity.Note;
import com.backend.Desktop.Repository.NoteRespository;
import com.backend.Desktop.Service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteRespository noteRespository;
    private final NoteService noteService;

    @GetMapping
    public List<Note> getAll(){
        return noteRespository.findAll();
    }

    @PostMapping
    public ResponseEntity<Note> basicCreation(@RequestBody Note note){
        return noteService.basicCreation(note);
    }

    @PostMapping("/{classId}/{studentId}")
    public ResponseEntity<Note> completeCreation(
            @RequestBody Note note,
            @PathVariable Integer classId,
            @PathVariable Integer studentId
    ){
        return noteService.completeCreation(note, classId, studentId);
    }

    @PutMapping("/{noteId}/{classId}/{studentId}")
    public ResponseEntity<Note> linkStudentAndClass(
            @PathVariable Integer noteId,
            @PathVariable Integer classId,
            @PathVariable Integer studentId
    ){
        return noteService.linkStudentAndClass(noteId, classId, studentId);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Note> deleteWithId(@PathVariable Integer noteId){
        return noteService.deleteWithId(noteId);
    }

}
