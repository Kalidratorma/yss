package com.kalidratorma.yss.controllers;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.kalidratorma.yss.entities.Task;
import com.kalidratorma.yss.repositories.TaskRepository;
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
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;

    @GetMapping
    public MappingJacksonValue readTasks() {
        List<Task> taskList = taskRepository.findAll();
        return getFilteredMapper(taskList, new CustomFilter("PlayerFilter",
                        SimpleBeanPropertyFilter.filterOutAllExcept("id", "surname", "name", "patronymic", "birthDate")),
                new CustomFilter("TaskFilter", SimpleBeanPropertyFilter.serializeAll()));

    }

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        taskRepository.save(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Task readTask(@PathVariable long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping
    public ResponseEntity<String> updateTask(@RequestBody Task task) {
        taskRepository.findById(task.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        taskRepository.save(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable long id) {
        Task origTask = taskRepository.findById(id).orElseThrow();
        taskRepository.deleteById(origTask.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/byPlayer/{playerId}")
    public MappingJacksonValue readPlayerTask(@PathVariable long playerId) {
        List<Task> taskList = taskRepository.findAllByPlayerId(playerId);
        return getFilteredMapper(taskList, new CustomFilter("TaskFilter",
                SimpleBeanPropertyFilter.serializeAllExcept("players")));
    }

}
