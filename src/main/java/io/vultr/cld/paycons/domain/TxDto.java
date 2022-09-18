package io.vultr.cld.paycons.domain;

import io.vultr.cld.paycons.util.AbstractJsonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TxDto extends AbstractJsonUtil {
    //id, ref, date, time, descr, type, amount
    private Long id;            //Transaction ID
    private String ref;         //Transaction Ref
    private String date;        //Transaction Date
    private String time;        //Transaction Time
    private String descr;       //Transaction Description
    private Character type;     //Transaction Type -> Debit or Credit.
    private Double amount;  //Transaction AMount
}
