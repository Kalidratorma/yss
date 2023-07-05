package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeasonRepository extends JpaRepository<Season, Long> {
    Optional<Season> findBySeason(short season);
}