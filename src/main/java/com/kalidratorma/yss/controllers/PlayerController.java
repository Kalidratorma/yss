package com.kalidratorma.yss.controllers;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.kalidratorma.yss.entities.Player;
import com.kalidratorma.yss.repositories.PlayerRepository;
import com.kalidratorma.yss.security.user.UserRepository;
import com.kalidratorma.yss.utils.CustomFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.kalidratorma.yss.utils.ControllerUtils.getFilteredMapper;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerController {

    private final PlayerRepository playerRepository;

    private final UserRepository userRepository;

    @GetMapping
    public MappingJacksonValue readPlayers() {
        List<Player> playerList = playerRepository.findAll();
        return getFilteredMapper(playerList, new CustomFilter("PlayerFilter",
                SimpleBeanPropertyFilter.serializeAll()));
    }

    @PostMapping
    public ResponseEntity<String> createPlayer(@RequestBody Player player) {
        playerRepository.save(player);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public MappingJacksonValue readPlayer(@PathVariable long id) {
        Player player = playerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        return getFilteredMapper(player, new CustomFilter("PlayerFilter",
                SimpleBeanPropertyFilter.serializeAll()));
    }

    @PutMapping
    public ResponseEntity<String> updatePlayer(@RequestBody Player player) {
        Player origPlayer = playerRepository.findByName(player.getName()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        userRepository
                .findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        ResponseEntity<String> response;
        player.setId(origPlayer.getId());
        playerRepository.save(player);
        response = new ResponseEntity<>(HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable long id) {
        Player origPlayer = playerRepository.findById(id).orElseThrow();
        playerRepository.deleteById(origPlayer.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/asFile/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody ResponseEntity<MappingJacksonValue> getPlayerAsFile(@PathVariable long id) {
        Player player = playerRepository.findById(id).orElseThrow();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-disposition"
                , "attachment; filename=" + id + ".json");
        responseHeaders.add("content-type"
                , "application/json");
        return new ResponseEntity<>(getFilteredMapper(player, new CustomFilter("PlayerFilter",
                SimpleBeanPropertyFilter.serializeAll()))
                , responseHeaders, HttpStatus.OK);
    }
}
