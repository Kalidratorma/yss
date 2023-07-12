package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.StatGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatGameRepository extends JpaRepository<StatGame, Long> {
}