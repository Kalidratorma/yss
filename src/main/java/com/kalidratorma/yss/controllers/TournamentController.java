package com.kalidratorma.yss.controllers;

import com.kalidratorma.yss.entities.Tournament;
import com.kalidratorma.yss.repositories.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@RequestMapping("/tournament")
public class TournamentController {

    private final TournamentRepository tournamentRepository;

    @GetMapping
    public List<Tournament> readTournaments() {
        return tournamentRepository.findAll();

    }

    @GetMapping("/{id}")
    public Tournament readTournament(@PathVariable long id) {
        return tournamentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<String> createTournament(@RequestBody Tournament Tournament) {
        tournamentRepository.save(Tournament);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateTournament(@RequestBody Tournament Tournament) {
        tournamentRepository.findById(Tournament.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        tournamentRepository.save(Tournament);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTournament(@PathVariable long id) {
        Tournament origTournament = tournamentRepository.findById(id).orElseThrow();
        tournamentRepository.deleteById(origTournament.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
