package bank_app.repo;

import bank_app.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends GenericRepo<Account> {
}
