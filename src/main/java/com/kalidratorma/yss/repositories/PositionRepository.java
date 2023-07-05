package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}