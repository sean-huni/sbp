package io.vultr.cld.paycons.persistence.entity;

import io.vultr.cld.paycons.util.AbstractJsonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Tx extends AbstractJsonUtil {
    //    id, ref, date, time, descr, type, amount
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ref;
    private LocalDate date;
    private LocalTime time;
    private String descr;
    private String type; //Debit or Credit.
    private BigDecimal amount;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "acc_id")
    private Account account;
}
