package BnkApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BnkApp.entity.Account;
import BnkApp.entity.Customer;
import BnkApp.repository.AccountRepo;
import BnkApp.repository.CustomerRepo;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Customer saveCustomer(Customer customer) {

        return customerRepo.save(customer);
    }

    @Override
    public Account createAccount(Account account) {

        return accountRepo.save(account);
    }



}
