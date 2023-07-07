package com.kalidratorma.yss.controllers;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.kalidratorma.yss.entities.StatFieldPlayer;
import com.kalidratorma.yss.repositories.StatFieldPlayerRepository;
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
@RequestMapping("/statFieldPlayer")
public class StatFieldPlayerController {

    private final StatFieldPlayerRepository statFieldPlayerRepository;

    @GetMapping
    public MappingJacksonValue readStatFieldPlayers() {
        List<StatFieldPlayer> statFieldPlayerList = statFieldPlayerRepository.findAll();
        return getFilteredMapper(statFieldPlayerList, new CustomFilter("PlayerFilter",
                SimpleBeanPropertyFilter.serializeAll()));
    }

    @PostMapping
    public ResponseEntity<String> createStatFieldPlayer(@RequestBody StatFieldPlayer statFieldPlayer) {
        statFieldPlayerRepository.save(statFieldPlayer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public MappingJacksonValue readStatFieldPlayer(@PathVariable long id) {
        StatFieldPlayer statFieldPlayer = statFieldPlayerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return  getFilteredMapper(statFieldPlayer, new CustomFilter("PlayerFilter",
                SimpleBeanPropertyFilter.serializeAll()));
    }

    @GetMapping("/byPlayer/{id}")
    public MappingJacksonValue findAllByPlayerId(@PathVariable long id) {
        List<StatFieldPlayer> statFieldPlayerList = statFieldPlayerRepository.findAllByPlayerId(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return getFilteredMapper(statFieldPlayerList, new CustomFilter("PlayerFilter",
                SimpleBeanPropertyFilter.serializeAll()));
    }

    @GetMapping("/byGame/{id}")
    public MappingJacksonValue findAllByGameId(@PathVariable long id) {
        List<StatFieldPlayer> statFieldPlayerList = statFieldPlayerRepository.findAllByGameId(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return getFilteredMapper(statFieldPlayerList, new CustomFilter("PlayerFilter",
                SimpleBeanPropertyFilter.serializeAll()));
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
