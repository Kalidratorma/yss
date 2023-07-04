package com.kalidratorma.yss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

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

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_seq")
    @SequenceGenerator(
            name = "contract_seq",
            sequenceName = "contract_seq",
            allocationSize = 1
    )
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
    @Temporal(TemporalType.DATE)
    private LocalDate expDate;

    /**
     * Условия оплаты
     */
    private String paymentTerms;
}
