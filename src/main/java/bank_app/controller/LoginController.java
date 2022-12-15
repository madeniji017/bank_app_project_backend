package bank_app.controller;

import bank_app.entity.Login;
import bank_app.entity.User;
import bank_app.error.UserNotFoundException;
import bank_app.service.AdminLoginService;
import bank_app.service.CustomerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class LoginController {

    @Autowired
    private CustomerLoginService customerLoginService;

    @Autowired
    private AdminLoginService adminLoginService;



    @PostMapping(value = "login", produces = "application/json", consumes = "application/json")
    public User customerLogin(@RequestBody Login login) throws UserNotFoundException {

        return customerLoginService.customerLogin(login);

    }

    @PostMapping(value = "/admin/login", produces = "application/json", consumes = "application/json")
    public User adminLogin(@RequestBody Login login) throws UserNotFoundException {

        return adminLoginService.adminLogin(login);

    }
}
