package com.kalidratorma.yss.controllers;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.kalidratorma.yss.entities.Training;
import com.kalidratorma.yss.repositories.TrainingRepository;
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
@RequestMapping("/training")
public class TrainingController {

    private final TrainingRepository trainingRepository;

    @GetMapping
    public MappingJacksonValue readTrainings() {
        List<Training> trainingList = trainingRepository.findAll();
        return getFilteredMapper(trainingList, new CustomFilter("PlayerFilter",
                SimpleBeanPropertyFilter.serializeAll()));
    }

    @PostMapping
    public ResponseEntity<String> createTraining(@RequestBody Training training) {
        trainingRepository.save(training);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public MappingJacksonValue readTraining(@PathVariable long id) {
        Training training = trainingRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return  getFilteredMapper(training, new CustomFilter("PlayerFilter",
                SimpleBeanPropertyFilter.serializeAll()));
    }

    @PutMapping
    public ResponseEntity<String> updateTraining(@RequestBody Training training) {
        trainingRepository.findById(training.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        trainingRepository.save(training);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTraining(@PathVariable long id) {
        Training origTraining = trainingRepository.findById(id).orElseThrow();
        trainingRepository.deleteById(origTraining.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
