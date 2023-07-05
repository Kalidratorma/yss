package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}