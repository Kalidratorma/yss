package com.kalidratorma.yss.controllers;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.kalidratorma.yss.entities.Player;
import com.kalidratorma.yss.entities.Task;
import com.kalidratorma.yss.entities.TaskReport;
import com.kalidratorma.yss.entities.requests.TaskReportRequest;
import com.kalidratorma.yss.repositories.PlayerRepository;
import com.kalidratorma.yss.repositories.TaskReportRepository;
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
@RequestMapping("/taskReport")
public class TaskReportController {

    private final TaskReportRepository taskReportRepository;
    private final TaskRepository taskRepository;
    private final PlayerRepository playerRepository;



    @GetMapping
    public MappingJacksonValue readTaskReports() {
        List<TaskReport> taskReportList = taskReportRepository.findAll();
        return getFilteredMapper(taskReportList, new CustomFilter("PlayerFilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("id", "surname", "name", "patronymic", "birthDate")),
                new CustomFilter("TaskFilter", SimpleBeanPropertyFilter.serializeAll()));

    }

    @PostMapping
    public ResponseEntity<String> createTaskReport(@RequestBody TaskReportRequest taskReportRequest) {
        Task task = taskRepository.findById(taskReportRequest.getTaskId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        Player player = playerRepository.findById(taskReportRequest.getPlayerId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        TaskReport taskReport = new TaskReport();
        taskReport.setTask(task);
        taskReport.setPlayer(player);
        taskReport.setReport(taskReportRequest.getReport());
        taskReport.setReportDate(taskReportRequest.getReportDate());
        taskReport.setTaskDate(taskReportRequest.getTaskDate());
        taskReport.setPhotoLinks(taskReportRequest.getPhotoLinks());
        taskReport.setVideoLinks(taskReportRequest.getVideoLinks());

        taskReportRepository.save(taskReport);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public TaskReport readTaskReport(@PathVariable long id) {
        return taskReportRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping
    public ResponseEntity<String> updateTaskReport(@RequestBody TaskReport taskReport) {
        TaskReport origTaskReport = taskReportRepository.findById(taskReport.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        taskReportRepository.save(taskReport);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskReport(@PathVariable long id) {
        TaskReport origTaskReport = taskReportRepository.findById(id).orElseThrow();
        taskReportRepository.deleteById(origTaskReport.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/byPlayer/{playerId}")
    public MappingJacksonValue readPlayerTaskReport(@PathVariable long playerId) {
        List<TaskReport> taskReportList = taskReportRepository.findAllByPlayerId(playerId);
        return getFilteredMapper(taskReportList, new CustomFilter("TaskFilter",
                SimpleBeanPropertyFilter.serializeAllExcept("players")));
    }

}
