package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.StatGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StatGameRepository extends JpaRepository<StatGame, Long> {

    Optional<StatGame> findByGameId(@Param("gameId") long gameId);
}