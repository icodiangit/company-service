package com.icodian.careervia.company.controller;

import com.icodian.careervia.company.service.HrService;
import com.icodian.careervia.companydto.HrDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hrs")
@RequiredArgsConstructor
public class HrController {

    private final HrService hrService;

    // CREATE HR
    @PostMapping("/add")
    public ResponseEntity<?> addHr(@RequestBody HrDTO hrDTO) {
        try {
            HrDTO createdHr = hrService.createHr(hrDTO);
            return ResponseEntity.ok(createdHr);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET HR BY ID
    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewHr(@PathVariable Long id) {
        try {
            HrDTO hr = hrService.getHrById(id);
            return ResponseEntity.ok(hr);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    //  GET ALL HRS 
    @GetMapping({"", "/list"})
    public ResponseEntity<List<HrDTO>> listHrs() {
        List<HrDTO> hrs = hrService.getAllHrs();
        return ResponseEntity.ok(hrs);
    }

    // UPDATE STATUS
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateHrStatus(@PathVariable Long id,
                                            @RequestParam String status) {
        try {
            HrDTO updatedHr = hrService.updateHrStatus(id, status);
            return ResponseEntity.ok(updatedHr);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid status value: " + status);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}