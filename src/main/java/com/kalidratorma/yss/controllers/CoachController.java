package com.kalidratorma.yss.controllers;

import com.kalidratorma.yss.entities.Coach;
import com.kalidratorma.yss.repositories.CoachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@RequestMapping("/coach")
public class CoachController {

    private final CoachRepository coachRepository;

    @GetMapping
    public List<Coach> readCoaches() {
        return coachRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> createCoach(@RequestBody Coach coach) {
        coachRepository.save(coach);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Coach readCoach(@PathVariable long id) {
        return coachRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/byUser/{userId}")
    public Coach readCoachByUserId(@PathVariable long userId) {
        return coachRepository.findCoachByUserId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<String> updateCoach(@RequestBody Coach coach) {
        coachRepository.findById(coach.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        coachRepository.save(coach);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCoach(@PathVariable long id) {
        Coach origCoach = coachRepository.findById(id).orElseThrow();
        coachRepository.deleteById(origCoach.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
