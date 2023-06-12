package com.kalidratorma.yss.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface ContentFileRepository extends JpaRepository<ContentFile, Long> {

    Optional<ContentFile> findTopByNameOrderByIdDesc(String name);
    List<ContentFile> findAll ();

    void deleteByName(String name);
}