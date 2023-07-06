package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.StatFieldPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StatFieldPlayerRepository extends JpaRepository<StatFieldPlayer, Long> {

    @Query("from StatFieldPlayer s inner join fetch s.player p where p.id = :id")
    Optional<List<StatFieldPlayer>> findAllByPlayerId(@Param("id") long id);

    @Query("from StatFieldPlayer s inner join fetch s.game g where g.id = :id")
    Optional<List<StatFieldPlayer>> findAllByGameId(@Param("id") long id);

}