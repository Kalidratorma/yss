package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.ClubTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubTeamRepository extends JpaRepository<ClubTeam, Long> {
    Optional<ClubTeam> findByName(String name);
}