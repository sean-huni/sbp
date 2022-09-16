package io.vultr.cld.paycons.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Tx {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String descr;
    private Character type; //Debit or Credit.
    private BigDecimal amount;
}
