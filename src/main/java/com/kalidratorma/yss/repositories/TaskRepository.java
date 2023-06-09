package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByName(String name);

    @Query("from Task r inner join fetch r.players where r.id = :id")
    List<Task> findAllByPlayerId(@Param("id") long id);
}