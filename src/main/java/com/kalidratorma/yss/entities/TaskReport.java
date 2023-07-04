package com.kalidratorma.yss.entities;

import com.kalidratorma.yss.file.ContentFile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Отчет по заданию тренера
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "task_report")
public class TaskReport {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_report_seq")
    @SequenceGenerator(
            name = "task_report_seq",
            sequenceName = "task_report_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Дата отчета
     */
    @Column(name = "report_date", updatable = false)
    @CreatedDate
    private Date reportDate;

    /**
     * Задание тренера
     */
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    /**
     * Спортсмен
     */
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    /**
     * Дата задания
     */
    @Column(name = "task_date")
    @Temporal(TemporalType.DATE)
    private LocalDate taskDate;

    /**
     * Текст отчета
     */
    @Column(name = "report")
    private String report;

    /**
     * Список ссылок с фотографиями
     */
    @OneToMany
    private List<ContentFile> photoLinks;

    /**
     * Список видео ссылок
     */
    @OneToMany
    private List<ContentFile> videoLinks;
}
