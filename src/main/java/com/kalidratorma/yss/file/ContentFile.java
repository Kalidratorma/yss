package com.kalidratorma.yss.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Файлы контента (фото, видео)
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "content_file")
public class ContentFile {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    /**
     * Имя файла с расширением без пути
     */
    String name;

    /**
     * Оригинальное имя файла
     */
    String originName;

    /**
     * Локальный физический полный путь к файлу
     */
    @JsonIgnore
    String location;

    public ContentFile(String name, String originName, String location) {
        this.name = name;
        this.originName = originName;
        this.location = location;
    }
}
