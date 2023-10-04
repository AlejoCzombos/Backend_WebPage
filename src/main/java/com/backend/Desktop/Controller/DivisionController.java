package com.backend.Desktop.Controller;


import com.backend.Desktop.Entity.Class;
import com.backend.Desktop.Entity.Division;
import com.backend.Desktop.Repository.DivisionRepository;
import com.backend.Desktop.Service.DivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/divisions")
public class DivisionController {

    private final DivisionService divisionService;
    private final DivisionRepository divisionRepository;

    @GetMapping
    public List<Division> getAll(){
        return divisionRepository.findAll();
    }

    @GetMapping("/{divisionId}")
    public ResponseEntity<Division> getById(@PathVariable Integer divisionId){
        return divisionService.getById(divisionId);
    }

    @PostMapping
    public ResponseEntity<Division> create(@RequestBody Division division){
        return divisionService.create(division);
    }

    @PutMapping("/{divisionId}/{studentsId}")
    public ResponseEntity<Division> addStudents(@PathVariable Integer divisionId, @PathVariable String[] studentsIds){
        return divisionService.linkStudents(divisionId, studentsIds);
    }

    @DeleteMapping("/{divisionId}")
    public ResponseEntity<Division> deleteById(@PathVariable Integer divisionId){
        return divisionService.deleteWithId(divisionId);
    }

}
