package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    Optional<Parent> findByName(String name);

//    @Query("from Parent r inner join fetch r.players where r.id = :id")
//    List<Parent> findAllByPlayerId(@Param("id") long id);
}