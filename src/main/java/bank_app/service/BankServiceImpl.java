package bank_app.service;

import bank_app.entity.Account;
import bank_app.entity.AccountNumber;
import bank_app.entity.User;
import bank_app.repo.AccountRepo;
import bank_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepo userRepo;

    private User user;

    private Account account;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private AccountNumber accountNumber;


    @Override
    public User saveUser(User user) {

        String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        
        return userRepo.save(user);
    }

    @Override
    public User fetchUserById(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public List<User> fetchUserList() {
        return userRepo.findAll();
    }

    @Override
    public Account createAccount(Long id) {
        user = userRepo.findById(id).get();
        Long userAcctNum = accountNumber.acctNumGenerator();
        account = new Account(user, userAcctNum);
        account.setAcctStatus("Active");
        account.setUser(user);
        accountRepo.save(account);

        return account;
    }

}
