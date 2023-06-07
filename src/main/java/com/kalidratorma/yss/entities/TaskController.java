package com.kalidratorma.yss.entities;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
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
public class TaskController {

    private final TaskRepository taskRepository;

    @GetMapping("/task")
    public MappingJacksonValue readTasks() {
        List<Task> taskList = taskRepository.findAll();
        return getFilteredMapper(taskList, "PlayerFilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("id", "surname", "name", "patronymic", "birthDate"));

    }

    @PostMapping("/task")
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        taskRepository.save(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/task/{id}")
    public Task readTask(@PathVariable long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping("/task")
    public ResponseEntity<String> updateTask(@RequestBody Task task) {
        Task origTask = taskRepository.findById(task.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        taskRepository.save(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable long id) {
        Task origTask = taskRepository.findById(id).orElseThrow();
        taskRepository.deleteById(origTask.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/player_tasks/{playerId}")
    public List<Task> readPlayerTask(@PathVariable long playerId) {
        return taskRepository.findAllByPlayerId(playerId);
    }

}
