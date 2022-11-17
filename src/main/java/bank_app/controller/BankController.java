package bank_app.controller;

import bank_app.entity.Login;
import bank_app.entity.User;
import bank_app.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/create-user")
    public User saveUser(@RequestBody User user) {

        return bankService.saveUser(user);
    }


}
