package io.vultr.cld.paycons.mapper;

import io.vultr.cld.paycons.domain.TxDto;
import io.vultr.cld.paycons.persistence.entity.Tx;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface TxMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TxDto convertToTxDto(Tx tx);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "date", target = "date", qualifiedByName = "toLocalDate")
    @Mapping(source = "time", target = "time", qualifiedByName = "toLocalTime")
    Tx convertToTx(TxDto txDto);

    @Named("toLocalDate")
    default LocalDate toLocalDate(final String date) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Named("toLocalTime")
    default LocalTime toLocalTime(final String time) {
        return LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
