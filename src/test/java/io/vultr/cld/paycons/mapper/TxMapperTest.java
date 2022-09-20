package io.vultr.cld.paycons.mapper;

import io.vultr.cld.paycons.domain.TxDto;
import io.vultr.cld.paycons.persistence.entity.Tx;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("h2")
@SpringBootTest
class TxMapperTest {
    @Autowired
    private TxMapper txMapper;
    private TxDto txDto;

    @BeforeEach
    void setUp() {
        txDto = TxDto.builder()
                .date("2022-09-16")
                .time("23:30:01")
                .descr("IFT Debit")
                .type('D')
                .amount(BigDecimal.valueOf(89.99))
                .build();
    }

    @Test
    void givenTxDto_whenMappingToTx_thenReturnTx() {
        //When
        final Tx tx = txMapper.convertToTx(txDto);

        //Then
        assertEquals(BigDecimal.valueOf(89.99), tx.getAmount());
        assertEquals(LocalDate.of(2022, 9, 16), tx.getDate());
        assertEquals(LocalTime.of(23, 30, 1), tx.getTime());
        assertEquals("IFT Debit", tx.getDescr());
        assertEquals("D", tx.getType());
    }
}