package com.kalidratorma.yss.controllers;

import com.kalidratorma.yss.entities.Contract;
import com.kalidratorma.yss.repositories.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@RequestMapping("/contract")
public class ContractController {

    private final ContractRepository contractRepository;

    @GetMapping
    public List<Contract> readContracts() {
        return contractRepository.findAll();
    }

    @GetMapping("/{id}")
    public Contract readContract(@PathVariable long id) {
        return contractRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping("/byParent/{id}")
    public List<Contract> readAllByParentId(@PathVariable long id) {
        return contractRepository.findAllByParentId(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping("/byPlayer/{id}")
    public List<Contract> readAllByPlayerId(@PathVariable long id) {
        return contractRepository.findAllByPlayerId(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<String> createContract(@RequestBody Contract contract) {
        contractRepository.save(contract);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateContract(@RequestBody Contract contract) {
        contractRepository.findById(contract.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        contractRepository.save(contract);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContract(@PathVariable long id) {
        Contract origContract = contractRepository.findById(id).orElseThrow();
        contractRepository.deleteById(origContract.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
