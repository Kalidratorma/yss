package com.kalidratorma.yss.controllers;

import com.kalidratorma.yss.entities.TeamYear;
import com.kalidratorma.yss.repositories.TeamYearRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@RequestMapping("/teamYear")
public class TeamYearController {

    private final TeamYearRepository teamYearRepository;

    @GetMapping
    public List<TeamYear> readTeamYears() {
        return teamYearRepository.findAll();

    }

    @GetMapping("/{id}")
    public TeamYear readTeamYear(@PathVariable long id) {
        return teamYearRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<String> createTeamYear(@RequestBody TeamYear TeamYear) {
        teamYearRepository.save(TeamYear);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateTeamYear(@RequestBody TeamYear TeamYear) {
        teamYearRepository.findById(TeamYear.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        teamYearRepository.save(TeamYear);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeamYear(@PathVariable long id) {
        TeamYear origTeamYear = teamYearRepository.findById(id).orElseThrow();
        teamYearRepository.deleteById(origTeamYear.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
