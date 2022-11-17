package bank_app.service;

import bank_app.entity.Login;
import bank_app.entity.PasswordEncoder;
import bank_app.entity.User;
import bank_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User userLogin(Login login) {


        User user = userRepo.findByEmailAddress(login.getEmailAddress());

        if (user != null) {

            //compare the password hash
            boolean confirmPassword = passwordEncoder.bCryptPasswordEncoder()
                    .matches(login.getPassword(), user.getPassword());

            if(confirmPassword) {

                return user;
            }

        }

        // return auser user with
        return null;

    }
}
