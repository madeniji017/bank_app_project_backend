package bank_app.controller;

import bank_app.dto.UserDTO;
import bank_app.entity.Account;
import bank_app.entity.User;
import bank_app.service.BankService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BankController {

    @Autowired
    private BankService bankService;


    //this API can be available on the Admin side as well
    @PostMapping("/create-user")
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {

        return bankService.saveUser(userDTO);
    }

    //will be available on admin side only
    @GetMapping("/users/{id}")
    public User fetchUserById(@PathVariable("id") Long id) {
        return bankService.fetchUserById(id);
    }

    //will be available on admin side only
    @GetMapping("/users")
    public List<UserDTO> fetchUserList() {

        return bankService.fetchUserList();
    }

    @PostMapping("/create-account/{id}")
    public Account createAccount(@PathVariable("id") Long id) {
        return bankService.createAccount(id);
    }

    //will be available on admin side only
    @DeleteMapping("/delete-account/{id}")
    public String deleteAccountById(@PathVariable("id") Long id) {
        bankService.deleteAccountById(id);
        return "Record successfully deleted";
    }


    @PutMapping("/update-users/{id}")
    public UserDTO updateUser(@PathVariable("id") Long id,
                           @RequestBody @Valid UserDTO userUpdate) {
        return bankService.updateUser(id, userUpdate);
    }



}
