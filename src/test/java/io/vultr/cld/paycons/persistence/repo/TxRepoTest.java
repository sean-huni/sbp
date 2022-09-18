package io.vultr.cld.paycons.persistence.repo;

import io.vultr.cld.paycons.persistence.entity.Tx;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("h2")
@DataJpaTest
class TxRepoTest {
    @Autowired
    private TxRepo txRepo;
    private String ref;

    @BeforeEach
    void setUp() {
        ref = UUID.randomUUID().toString();
    }

    @Test
    void givenTxRepo_whenSavingNewEntry_thenReturnSavedTx() {

        final Tx tx = Tx.builder().ref(ref).date(LocalDate.now()).time(LocalTime.now()).descr("IFT Withdrawal").type("C").amount(89.99D).build();

        txRepo.save(tx);
        final Tx txResp = txRepo.findById(1L).get();

        assertNotNull(txResp);
        assertEquals("IFT Withdrawal", txResp.getDescr());
        assertEquals(89.99, txResp.getAmount().doubleValue());
        assertEquals(ref, txResp.getRef());
    }
}