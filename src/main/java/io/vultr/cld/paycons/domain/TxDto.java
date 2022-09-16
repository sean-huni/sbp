package io.vultr.cld.paycons.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class TxDto {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String descr;
    private Character type; //Debit or Credit.
    private BigDecimal amount;
}
