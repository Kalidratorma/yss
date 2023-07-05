package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.TaskReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskReportRepository extends JpaRepository<TaskReport, Long> {

    @Query("from TaskReport r inner join fetch r.player t where t.id = :id")
    List<TaskReport> findAllByPlayerId(@Param("id") long id);

    @Query("from TaskReport r inner join fetch r.task t  inner join fetch t.coach c  where c.id = :id")
    List<TaskReport> findAllByCoachId(@Param("id") long id);
}