package com.backend.Desktop.Controller;

import com.backend.Desktop.Entity.Parent;
import com.backend.Desktop.Entity.Student;
import com.backend.Desktop.Repository.ParentRepository;
import com.backend.Desktop.Repository.StudentRepository;
import com.backend.Desktop.Service.ParentService;
import com.backend.Desktop.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parents")
public class ParentController {

    private final ParentService parentService;
    private final ParentRepository parentRepository;

    @GetMapping()
    public List<Parent> getAll(){
        return parentRepository.findAll();
    }

    @GetMapping("/{parentId}")
    public ResponseEntity<Parent> getById(@PathVariable Integer parentId){
        return parentService.getById(parentId);
    }

    @PostMapping("/{parentId}/{studentId}")
    public ResponseEntity<Parent> linkChildren(@PathVariable Integer parentId, @PathVariable Integer studentId){
        return parentService.linkChildren( parentId, studentId);
    }

    @PostMapping()
    public ResponseEntity<Parent> create(@RequestBody Parent parent){
        return parentService.create(parent);
    }

    @DeleteMapping("{parentId}")
    public ResponseEntity<Parent> delete(@PathVariable Integer parentId){
        return parentService.deleteById(parentId);
    }

}
