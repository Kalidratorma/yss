package com.kalidratorma.yss.controllers;

import com.kalidratorma.yss.entities.ClubTeam;
import com.kalidratorma.yss.repositories.ClubTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@RequestMapping("/clubTeam")
public class ClubTeamController {

    private final ClubTeamRepository clubTeamRepository;

    @GetMapping
    public List<ClubTeam> readClubTeams() {
        return clubTeamRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> createClubTeam(@RequestBody ClubTeam clubTeam) {
        clubTeamRepository.save(clubTeam);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ClubTeam readClubTeam(@PathVariable long id) {
        return clubTeamRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping("/byName/{name}")
    public ClubTeam readClubTeamByName(@PathVariable String name) {
        return clubTeamRepository.findByName(name).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping
    public ResponseEntity<String> updateClubTeam(@RequestBody ClubTeam clubTeam) {
        clubTeamRepository.findById(clubTeam.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        clubTeamRepository.save(clubTeam);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClubTeam(@PathVariable long id) {
        ClubTeam origClubTeam = clubTeamRepository.findById(id).orElseThrow();
        clubTeamRepository.deleteById(origClubTeam.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
