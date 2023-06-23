package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.GameFormat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameFormatRepository extends JpaRepository<GameFormat, Long> {
    Optional<GameFormat> findByName(String name);
}