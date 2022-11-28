package bank_app.service;

import bank_app.entity.Login;
import bank_app.entity.PasswordEncoder;
import bank_app.entity.User;
import bank_app.exception.ApiRequestException;
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


        User user = userRepo.findByEmail(login.getEmail());

        if (user != null) {

            boolean confirmPassword = passwordEncoder.bCryptPasswordEncoder()
                    .matches(login.getPassword(), user.getPassword());

            if(confirmPassword) {

                return user;
            }

        } else {
            throw new ApiRequestException("Invalid email/password");
        }

        return null;
    }
}
