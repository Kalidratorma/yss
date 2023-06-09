package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    Optional<Coach> findByName(String name);

    @Query("from Coach r inner join fetch r.user t where t.id = :id")
    Optional<Coach> findCoachByUserId(@Param("id") long id);
}