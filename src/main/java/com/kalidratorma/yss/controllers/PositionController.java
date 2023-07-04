package com.kalidratorma.yss.controllers;

import com.kalidratorma.yss.entities.Position;
import com.kalidratorma.yss.repositories.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@RequestMapping("/position")
public class PositionController {

    private final PositionRepository PositionRepository;

    @GetMapping
    public List<Position> readPositions() {
        return PositionRepository.findAll();

    }

    @GetMapping("/{id}")
    public Position readPosition(@PathVariable long id) {
        return PositionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<String> createPosition(@RequestBody Position Position) {
        PositionRepository.save(Position);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updatePosition(@RequestBody Position Position) {
        PositionRepository.findById(Position.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        PositionRepository.save(Position);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePosition(@PathVariable long id) {
        Position origPosition = PositionRepository.findById(id).orElseThrow();
        PositionRepository.deleteById(origPosition.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
