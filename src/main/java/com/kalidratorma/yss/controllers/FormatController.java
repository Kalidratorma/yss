package com.kalidratorma.yss.controllers;

import com.kalidratorma.yss.entities.GameFormat;
import com.kalidratorma.yss.repositories.GameFormatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@RequestMapping("/gameFormat")
public class FormatController {

    private final GameFormatRepository gameFormatRepository;

    @GetMapping
    public List<GameFormat> readGameFormats() {
        return gameFormatRepository.findAll();

    }

    @GetMapping("/{id}")
    public GameFormat readGameFormat(@PathVariable long id) {
        return gameFormatRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<String> createGameFormat(@RequestBody GameFormat gameFormat) {
        gameFormatRepository.save(gameFormat);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateGameFormat(@RequestBody GameFormat gameFormat) {
        GameFormat origGameFormat = gameFormatRepository.findById(gameFormat.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        gameFormatRepository.save(gameFormat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGameFormat(@PathVariable long id) {
        GameFormat origGameFormat = gameFormatRepository.findById(id).orElseThrow();
        gameFormatRepository.deleteById(origGameFormat.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
