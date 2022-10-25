package io.vultr.cld.paycons.domain;

import io.vultr.cld.paycons.util.AbstractJsonUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
public class AccountDto extends AbstractJsonUtil {
    private Long id;
    private Long no; //Account-Number
    private String name; //Client-Name
    private String descr; //Description
    private Collection<TxDto> transactions;
}
