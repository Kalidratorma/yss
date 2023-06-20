package com.kalidratorma.yss.entities;

import com.kalidratorma.yss.file.ContentFile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "task_report")
public class TaskReport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="task_report_seq")
    @SequenceGenerator(
            name="task_report_seq",
            sequenceName="task_report_seq",
            allocationSize=1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "report_date", nullable = false)
    private Date reportDate;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(name = "task_date")
    private Date taskDate;

    @Column(name = "report")
    private String report;

    @OneToMany
    private List<ContentFile> photoLinks;

    @OneToMany
    private List<ContentFile> videoLinks;
}
