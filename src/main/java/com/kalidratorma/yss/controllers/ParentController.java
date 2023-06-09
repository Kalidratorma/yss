package com.kalidratorma.yss.controllers;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.kalidratorma.yss.entities.Parent;
import com.kalidratorma.yss.repositories.ParentRepository;
import com.kalidratorma.yss.utils.CustomFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.kalidratorma.yss.utils.ControllerUtils.getFilteredMapper;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@RequestMapping("/parent")
public class ParentController {

    private final ParentRepository parentRepository;

    @GetMapping
    public MappingJacksonValue readParents() {
        List<Parent> parentList = parentRepository.findAll();
        return getFilteredMapper(parentList, new CustomFilter("PlayerFilter",
                SimpleBeanPropertyFilter.serializeAll()));
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
        Parent origParent = parentRepository.findById(parent.getId()).orElseThrow(
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

}
