package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.TeamYear;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamYearRepository extends JpaRepository<TeamYear, Long> {
}