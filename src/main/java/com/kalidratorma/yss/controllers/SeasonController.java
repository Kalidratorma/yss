package com.kalidratorma.yss.controllers;

import com.kalidratorma.yss.entities.Season;
import com.kalidratorma.yss.repositories.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@RequestMapping("/season")
public class SeasonController {

    private final SeasonRepository seasonRepository;

    @GetMapping
    public List<Season> readSeasons() {
        return seasonRepository.findAll();

    }

    @GetMapping("/{id}")
    public Season readSeason(@PathVariable long id) {
        return seasonRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping("/bySeason/{season}")
    public Season readSeason(@PathVariable short season) {
        return seasonRepository.findBySeason(season).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<String> createSeason(@RequestBody Season Season) {
        seasonRepository.save(Season);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateSeason(@RequestBody Season Season) {
        seasonRepository.findById(Season.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        seasonRepository.save(Season);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeason(@PathVariable long id) {
        Season origSeason = seasonRepository.findById(id).orElseThrow();
        seasonRepository.deleteById(origSeason.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
