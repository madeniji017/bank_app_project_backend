package bank_app.service;

import bank_app.entity.Login;
import bank_app.entity.User;
import bank_app.error.UserNotFoundException;
import bank_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdminLoginService {

    @Autowired
    private UserRepo userRepo;

    public User adminLogin(Login login) throws UserNotFoundException {


        User admin = userRepo.findByEmail(login.getEmail());

        if (admin == null) {

            throw new UserNotFoundException("User not found");


        } else {

            if (!(Objects.equals(admin.getPassword(), login.getPassword()) &&
                    admin.getRole().getId() == 1)) {

                throw new UserNotFoundException("Invalid login details");

            }

            return admin;
        }

    }

}
