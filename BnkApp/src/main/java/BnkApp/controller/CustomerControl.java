package BnkApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import BnkApp.entity.Account;
import BnkApp.entity.Customer;
import BnkApp.service.BankService;

@RestController
@RequestMapping("api/v1")
public class CustomerControl {

    @Autowired
    private BankService bankService;

    @PostMapping("/update-details")
    public Customer saveCustomer(@RequestBody Customer customer) {

        return bankService.saveCustomer(customer);
    }

    @PostMapping("/account")
    public Account createAccount(@RequestBody Account account) {

        return bankService.createAccount(account);
    }

}
