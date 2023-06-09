package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    Optional<Coach> findByName(String name);

//    @Query("from Coach r inner join fetch r.players where r.id = :id")
//    List<Coach> findAllByPlayerId(@Param("id") long id);
}