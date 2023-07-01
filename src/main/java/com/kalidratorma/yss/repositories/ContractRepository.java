package com.kalidratorma.yss.repositories;

import com.kalidratorma.yss.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query(value = "SELECT c.* FROM contract c" +
            "                inner join parent_contracts pc on c.id=pc.contracts_id" +
            "       WHERE pc.parent_id = :id",
            nativeQuery = true)
    Optional<List<Contract>> findAllByParentId(@Param("id") long id);

    @Query(value = "SELECT c.* FROM contract c" +
            "                inner join player_contracts pc on c.id=pc.contracts_id" +
            "       WHERE pc.player_id = :id",
            nativeQuery = true)
    Optional<List<Contract>> findAllByPlayerId(@Param("id") long id);
}