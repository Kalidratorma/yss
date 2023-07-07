package com.kalidratorma.yss.controllers;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.kalidratorma.yss.entities.Game;
import com.kalidratorma.yss.repositories.GameRepository;
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
@RequestMapping("/game")
public class GameController {

    private final GameRepository gameRepository;

    @GetMapping
    public MappingJacksonValue readGames() {
        List<Game> gameList = gameRepository.findAll();
        return getFilteredMapper(gameList, new CustomFilter("PlayerFilter",
                SimpleBeanPropertyFilter.serializeAll()));
    }

    @PostMapping
    public ResponseEntity<String> createGame(@RequestBody Game game) {
        gameRepository.save(game);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public MappingJacksonValue readGame(@PathVariable long id) {
        Game game = gameRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return  getFilteredMapper(game, new CustomFilter("PlayerFilter",
                SimpleBeanPropertyFilter.serializeAll()));
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
