package com.backend.Desktop.Controller;

import com.backend.Desktop.DTO.NoteDTO;
import com.backend.Desktop.Entity.Note;
import com.backend.Desktop.Repository.NoteRespository;
import com.backend.Desktop.Service.NoteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteRespository noteRespository;
    private final NoteService noteService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<Note> getAll() {
        return noteRespository.findAll();
    }

    @PostMapping
    public ResponseEntity<Note> basicCreation(@RequestBody Note note) {
        return noteService.basicCreation(note);
    }

    @GetMapping("/student/{studentId}")
    public List<NoteDTO> notesByStudentId(@PathVariable Integer studentId){

        List<Note> notes = noteRespository.findAllByStudentId(studentId);

        List<NoteDTO> noteDTOs = notes.stream()
                .map(note -> {
                    NoteDTO dto = modelMapper.map(note, NoteDTO.class);
                    dto.setClass_name(note.getAClass().getClass_name());
                    return dto;
                })
                .collect(Collectors.toList());

        Collections.sort(noteDTOs, Comparator
                .comparing(NoteDTO::getClass_name)
                .thenComparingInt(NoteDTO::getQuarter));
        return noteDTOs;
    }

    @PostMapping("/{classId}/{studentId}")
    public ResponseEntity<Note> completeCreation(
            @RequestBody Note note,
            @PathVariable Integer classId,
            @PathVariable Integer studentId
    ) {
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
