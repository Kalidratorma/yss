package com.kalidratorma.yss.controllers;

import com.kalidratorma.yss.entities.TrainingFormat;
import com.kalidratorma.yss.repositories.TrainingFormatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@RequestMapping("/trainingFormat")
public class TrainingFormatController {

    private final TrainingFormatRepository trainingFormatRepository;

    @GetMapping
    public List<TrainingFormat> readTrainingFormats() {
        return trainingFormatRepository.findAll();

    }

    @GetMapping("/{id}")
    public TrainingFormat readTrainingFormat(@PathVariable long id) {
        return trainingFormatRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<String> createTrainingFormat(@RequestBody TrainingFormat trainingFormat) {
        trainingFormatRepository.save(trainingFormat);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateTrainingFormat(@RequestBody TrainingFormat trainingFormat) {
        trainingFormatRepository.findById(trainingFormat.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        trainingFormatRepository.save(trainingFormat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrainingFormat(@PathVariable long id) {
        TrainingFormat origTrainingFormat = trainingFormatRepository.findById(id).orElseThrow();
        trainingFormatRepository.deleteById(origTrainingFormat.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
