package io.vultr.cld.paycons.mapper;

import io.vultr.cld.paycons.domain.TxDto;
import io.vultr.cld.paycons.persistence.entity.Tx;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TxMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TxDto convertToTxDto(Tx tx);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Tx convertToTx(TxDto txDto);
}
