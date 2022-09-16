package io.vultr.cld.paycons.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;

import static java.util.Objects.nonNull;

@Setter
@Getter
public class AccountDto {
    private Long accNo; //Account-Number
    private String name; //Client-Name
    private String descr; //Description
    @Setter(AccessLevel.NONE)
    private Collection<TxDto> transactions;

    public void setTransactions(final Collection<TxDto> transactions) {
        if (nonNull(transactions)) {
            this.transactions = new HashSet<>();
            this.transactions.addAll(transactions);
        }
    }
}
