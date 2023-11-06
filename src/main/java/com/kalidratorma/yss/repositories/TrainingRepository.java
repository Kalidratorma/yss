package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}