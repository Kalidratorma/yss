package com.kalidratorma.yss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * Договор
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Номер договора
     */
    private String contractNumber;

    /**
     * Номер договора контрагента
     */
    private String contractorContractNumber;

    /**
     * Предмет договора
     */
    private String contractSubject;

    /**
     * Тип договора
     */
    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    /**
     * Срок действия договора
     */
    private Date expDate;

    /**
     * Условия оплаты
     */
    private String paymentTerms;
}