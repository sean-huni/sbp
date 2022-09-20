package io.vultr.cld.paycons.persistence.entity;

import io.vultr.cld.paycons.domain.TxDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class Account {
    private Long id;
    private Long accNo; //Account-Number
    private String name; //Client-Name
    private String descr; //Description
    @Setter(AccessLevel.NONE)
    private Collection<TxDto> transactions;
}
