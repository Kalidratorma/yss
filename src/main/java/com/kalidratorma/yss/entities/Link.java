package com.kalidratorma.yss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "link")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="link_seq")
    @SequenceGenerator(
            name="link_seq",
            sequenceName="link_seq",
            allocationSize=1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "url", updatable = false)
    private String url;
}
