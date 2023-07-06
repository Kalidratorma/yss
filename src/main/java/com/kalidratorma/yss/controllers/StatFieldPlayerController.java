package com.kalidratorma.yss.controllers;

import com.kalidratorma.yss.entities.StatFieldPlayer;
import com.kalidratorma.yss.repositories.StatFieldPlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@RequestMapping("/statFieldPlayer")
public class StatFieldPlayerController {

    private final StatFieldPlayerRepository statFieldPlayerRepository;

    @GetMapping
    public List<StatFieldPlayer> readStatFieldPlayers() {
        return statFieldPlayerRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> createStatFieldPlayer(@RequestBody StatFieldPlayer statFieldPlayer) {
        statFieldPlayerRepository.save(statFieldPlayer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public StatFieldPlayer readStatFieldPlayer(@PathVariable long id) {
        return statFieldPlayerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping("/byPlayer/{id}")
    public List<StatFieldPlayer> findAllByPlayerId(@PathVariable long id) {
        return statFieldPlayerRepository.findAllByPlayerId(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping("/byGame/{id}")
    public List<StatFieldPlayer> findAllByGameId(@PathVariable long id) {
        return statFieldPlayerRepository.findAllByGameId(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping
    public ResponseEntity<String> updateStatFieldPlayer(@RequestBody StatFieldPlayer statFieldPlayer) {
        statFieldPlayerRepository.findById(statFieldPlayer.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        statFieldPlayerRepository.save(statFieldPlayer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStatFieldPlayer(@PathVariable long id) {
        StatFieldPlayer origStatFieldPlayer = statFieldPlayerRepository.findById(id).orElseThrow();
        statFieldPlayerRepository.deleteById(origStatFieldPlayer.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
