package BnkApp.service;

import BnkApp.entity.Account;
import BnkApp.entity.Customer;

public interface BankService {
    Customer saveCustomer(Customer customer);

    Account createAccount(Account account);
}
