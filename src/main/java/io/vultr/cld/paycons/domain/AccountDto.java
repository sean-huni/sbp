package io.vultr.cld.paycons.domain;

import io.vultr.cld.paycons.util.AbstractJsonUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Setter
@Getter
public class AccountDto extends AbstractJsonUtil {
    private Long id;
    private Long no; //Account-Number
    private String name; //Client-Name
    private String descr; //Description
    @Setter(AccessLevel.NONE)
    private Collection<TxDto> transactions;

    public void setTransactions(final Collection<TxDto> transactions) {
        if (isNull(transactions)) {
            this.transactions = new ArrayList<>();
        }

        if (nonNull(transactions)) {
            this.transactions.addAll(transactions);
        }
    }
}
