package BnkApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import BnkApp.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}
