package ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.entity.Section;
import ie.dcu.mail.zhang7.bin.ee417.groupk.assignment.jpa.SectionRepository;

import java.util.*;

@RestController
public class SectionController {

    @Autowired
    private SectionRepository sectionRepository;

    @GetMapping("/sections")
    public List<Section> getSections() {

        return sectionRepository.findAll();
    }
    
    @GetMapping("/section")
    public Optional<Section> getSections(@RequestParam int id) {

        return sectionRepository.findById(id);
    }

    @PostMapping("/section_update")
    public ResponseEntity<Map<String, Object>> updateSection(Authentication auth, @RequestBody Section section) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (section.getId() == 0) {
                // Create a new record
                Section savedSection = sectionRepository.save(section);
                response.put("success", 1);
                response.put("id", savedSection.getId());
            } else {
                // Update an existing record
                Section existingSection = sectionRepository.findById(section.getId()).orElse(null);
                if (existingSection != null) {
                    existingSection.setName(section.getName());
                    existingSection.setDescription(section.getDescription());
                    sectionRepository.save(existingSection);
                    response.put("success", 1);
                    response.put("id", existingSection.getId());
                } else {
                    response.put("success", 0);
                    response.put("error", "Section with id " + section.getId() + " not found.");
                }
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.put("success", 0);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/section_delete")
    public ResponseEntity<Map<String, Object>> deleteSection(Authentication auth, @RequestParam int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            sectionRepository.deleteById(id);
            response.put("success", 1);
            response.put("id", id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.put("success", 0);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
