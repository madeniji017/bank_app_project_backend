package bank_app.service;

import bank_app.entity.Login;
import bank_app.entity.PasswordEncoder;
import bank_app.entity.User;
import bank_app.exception.ApiRequestException;
import bank_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerLoginService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User customerLogin(Login login) {


        User customer = userRepo.findByEmail(login.getEmail());

        if (customer != null) {

            boolean confirmPassword = passwordEncoder.bCryptPasswordEncoder()
                    .matches(login.getPassword(), customer.getPassword());

            if(confirmPassword && customer.getRole().getId() == 2) {

                return customer;
            }

        } else {

            throw new ApiRequestException("Invalid email/password");
        }

        return null;
    }
}
