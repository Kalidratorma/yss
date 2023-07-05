package com.kalidratorma.yss.controllers;

import com.kalidratorma.yss.entities.Game;
import com.kalidratorma.yss.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameRepository gameRepository;

    @GetMapping
    public List<Game> readGames() {
        return gameRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> createGame(@RequestBody Game game) {
        gameRepository.save(game);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Game readGame(@PathVariable long id) {
        return gameRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping
    public ResponseEntity<String> updateGame(@RequestBody Game game) {
        gameRepository.findById(game.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        gameRepository.save(game);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable long id) {
        Game origGame = gameRepository.findById(id).orElseThrow();
        gameRepository.deleteById(origGame.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
