package com.kalidratorma.yss.controllers;

import com.kalidratorma.yss.entities.Club;
import com.kalidratorma.yss.repositories.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final ClubRepository clubRepository;

    @GetMapping
    public List<Club> readClubs() {
        return clubRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> createClub(@RequestBody Club club) {
        clubRepository.save(club);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Club readClub(@PathVariable long id) {
        return clubRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping("/byName/{name}")
    public Club readClubByName(@PathVariable String name) {
        return clubRepository.findByName(name).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping
    public ResponseEntity<String> updateClub(@RequestBody Club club) {
        clubRepository.findById(club.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        clubRepository.save(club);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClub(@PathVariable long id) {
        Club origClub = clubRepository.findById(id).orElseThrow();
        clubRepository.deleteById(origClub.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
