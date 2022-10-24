package io.vultr.cld.paycons.persistence.repo;


import io.vultr.cld.paycons.persistence.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
}
