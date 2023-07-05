package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}