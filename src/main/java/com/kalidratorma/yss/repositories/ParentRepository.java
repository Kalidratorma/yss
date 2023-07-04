package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    @Query("from Parent r inner join fetch r.user t where t.id = :id")
    Optional<Parent> findParentByUserId(@Param("id") long id);
}