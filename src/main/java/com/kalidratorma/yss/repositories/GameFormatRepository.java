package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.GameFormat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameFormatRepository extends JpaRepository<GameFormat, Long> {
}