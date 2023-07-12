package com.kalidratorma.yss.controllers;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.kalidratorma.yss.entities.StatGame;
import com.kalidratorma.yss.repositories.StatGameRepository;
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
@RequestMapping("/statGame")
public class StatGameController {

    private final StatGameRepository statGameRepository;

    @GetMapping
    public MappingJacksonValue readStatGames() {
        List<StatGame> statGameList = statGameRepository.findAll();
        return getFilteredMapper(statGameList, new CustomFilter("PlayerFilter",
                SimpleBeanPropertyFilter.filterOutAll()));
    }

    @PostMapping
    public ResponseEntity<String> createStatGame(@RequestBody StatGame statGame) {
        statGameRepository.save(statGame);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public MappingJacksonValue readStatGame(@PathVariable long id) {
        StatGame statGame = statGameRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return getFilteredMapper(statGame, new CustomFilter("PlayerFilter",
                SimpleBeanPropertyFilter.filterOutAll()));
    }

    @PutMapping
    public ResponseEntity<String> updateStatGame(@RequestBody StatGame statGame) {
        statGameRepository.findById(statGame.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        statGameRepository.save(statGame);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStatGame(@PathVariable long id) {
        StatGame origStatGame = statGameRepository.findById(id).orElseThrow();
        statGameRepository.deleteById(origStatGame.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
