package bank_app.controller;

import bank_app.dto.UserDTO;
import bank_app.entity.User;
import bank_app.error.BadRequestException;
import bank_app.error.UserNotFoundException;
import bank_app.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4000")
@RestController
@RequestMapping("api/v1")
public class BankController {

    @Autowired
    private BankService bankService;


    //this API can be available on the Admin side as well
    @PostMapping("/create-user")
    public UserDTO createUser(@RequestBody UserDTO userDTO) throws BadRequestException {

        return bankService.createUser(userDTO);
    }

    //will be available on admin side only
    @GetMapping("/user")
    public User fetchUserByEmail(@RequestBody UserDTO userDTO) throws UserNotFoundException {

        return bankService.fetchUserByEmail(userDTO);
    }

    //will be available on admin side only
    @GetMapping("/user-list")
    public List<UserDTO> fetchUserList() {

        return bankService.fetchUserList();
    }

    //will be available on admin side only
    @DeleteMapping("/delete-account")
    public String deleteAccountByEmail(@RequestBody UserDTO userDTO) throws UserNotFoundException {
        bankService.deleteAccountByEmail(userDTO);
        return "Record successfully deleted";
    }

    @PutMapping("/update-user")
    public UserDTO updateUser(@RequestBody @Valid UserDTO userUpdate) throws UserNotFoundException {
        return bankService.updateUser(userUpdate);
    }

}
