package com.kalidratorma.yss.controllers;

import com.kalidratorma.yss.entities.Parent;
import com.kalidratorma.yss.repositories.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@RequestMapping("/parent")
public class ParentController {

    private final ParentRepository parentRepository;

    @GetMapping
    public List<Parent> readParents() {
        return parentRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> createParent(@RequestBody Parent parent) {
        parentRepository.save(parent);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Parent readParent(@PathVariable long id) {
        return parentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping
    public ResponseEntity<String> updateParent(@RequestBody Parent parent) {
        parentRepository.findById(parent.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        parentRepository.save(parent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParent(@PathVariable long id) {
        Parent origParent = parentRepository.findById(id).orElseThrow();
        parentRepository.deleteById(origParent.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/byUser/{userId}")
    public Parent readCoachByUserId(@PathVariable long userId) {
        return parentRepository.findParentByUserId(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

}
