package io.vultr.cld.paycons.persistence.repo;

import io.vultr.cld.paycons.persistence.entity.Tx;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("h2")
@DataJpaTest
class TxRepoTest {
    @Autowired
    private TxRepo txRepo;

    @Test
    void givenTxRepo_whenSavingNewEntry_thenReturnSavedTx() {
        final Tx tx = Tx.builder()
                .date(LocalDate.now())
                .time(LocalTime.now())
                .descr("IFT Withdrawal")
                .type("C")
                .amount(BigDecimal.valueOf(89.99))
                .build();

        txRepo.save(tx);
        final Tx txResp = txRepo.findById(1L).get();

        assertNotNull(txResp);
        assertEquals("IFT Withdrawal", txResp.getDescr());
        assertEquals(89.99, txResp.getAmount().doubleValue());
    }
}