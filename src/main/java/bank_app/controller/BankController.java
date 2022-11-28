package bank_app.controller;

import bank_app.entity.Account;
import bank_app.entity.Login;
import bank_app.entity.User;
import bank_app.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/create-user")
    public User saveUser(@RequestBody User user) {

        return bankService.saveUser(user);
    }

    @GetMapping("/users/{id}")
    public User fetchUserById(@PathVariable("id") Long id) {
        return bankService.fetchUserById(id);
    }

    @GetMapping("/users")
    public List<User> fetchUserList() {
        return bankService.fetchUserList();
    }

    @PostMapping("/create-account/{id}")
    public Account createAccount(@PathVariable("id") Long id) {
        return bankService.createAccount(id);
    }

    @DeleteMapping("/delete-account/{id}")
    public String deleteAccountById(@PathVariable("id") Long id) {
        bankService.deleteAccountById(id);
        return "Record successfully deleted";
    }

    @PutMapping("/update-users/{id}")
    public User updateUser(@PathVariable("id") Long id,
                           @RequestBody User userUpdate) {
        return bankService.updateUser(id, userUpdate);
    }


}
