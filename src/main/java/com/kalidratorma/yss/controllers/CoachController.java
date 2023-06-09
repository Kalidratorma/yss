package com.kalidratorma.yss.controllers;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.kalidratorma.yss.entities.Coach;
import com.kalidratorma.yss.repositories.CoachRepository;
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
@RequestMapping("/coach")
public class CoachController {

    private final CoachRepository coachRepository;

    @GetMapping
    public MappingJacksonValue readCoaches() {
        List<Coach> coachList = coachRepository.findAll();
        return getFilteredMapper(coachList, new CustomFilter("PlayerFilter",
                SimpleBeanPropertyFilter.serializeAll()));
    }

    @PostMapping
    public ResponseEntity<String> createCoach(@RequestBody Coach coach) {
        coachRepository.save(coach);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Coach readCoach(@PathVariable long id) {
        return coachRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping("/byUser/{userId}")
    public Coach readCoachByUserId(@PathVariable long userId) {
        return coachRepository.findCoachByUserId(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping
    public ResponseEntity<String> updateCoach(@RequestBody Coach coach) {
        Coach origCoach = coachRepository.findById(coach.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
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
