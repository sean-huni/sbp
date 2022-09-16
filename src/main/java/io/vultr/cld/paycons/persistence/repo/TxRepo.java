package io.vultr.cld.paycons.persistence.repo;

import io.vultr.cld.paycons.persistence.entity.Tx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TxRepo extends JpaRepository<Long, Tx> {
}
