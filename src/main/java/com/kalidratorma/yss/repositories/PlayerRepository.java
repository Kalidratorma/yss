package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByName(String name);

    @Query("from Player r inner join fetch r.parents t where t.id" +
            " = :id")
    List<Player> findPlayersByParentId(@Param("id") long id);
}