package com.backend.Desktop.Controller;

import com.backend.Desktop.Entity.Fee;
import com.backend.Desktop.Entity.Note;
import com.backend.Desktop.Repository.FeeRepository;
import com.backend.Desktop.Service.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/fees")
@RestController
public class FeeController {

    private final FeeService feeService;
    private final FeeRepository feeRepository;

    @GetMapping
    public List<Fee> getAll(){
        return feeRepository.findAll();
    }

    @PostMapping("/{parentId}/{studentId}")
    public ResponseEntity<Fee> completeCreation(
            @RequestBody Fee fee,
            @PathVariable Integer parentId,
            @PathVariable Integer studentId
    ) {
        return feeService.completeCreation(fee, parentId, studentId);
    }

    @DeleteMapping("/{feeId}")
    public ResponseEntity<Fee> deleteWithId(@PathVariable Integer feeId){
        return feeService.deleteWithId(feeId);
    }

}
