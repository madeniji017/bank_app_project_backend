package BnkApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import BnkApp.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {
}
