package bank_app.service;

import bank_app.entity.Account;
import bank_app.entity.AccountNumber;
import bank_app.entity.Role;
import bank_app.entity.User;
import bank_app.repo.AccountRepo;
import bank_app.repo.GenericRepo;
import bank_app.repo.RoleRepo;
import bank_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private User user;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;
    private Role role;

    private Account account;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private AccountNumber accountNumber;

    @Override
    public User saveUser(User user) {

        //encrypt the password provided by the user
        String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        //assign from the db the role type with the id of 2
        Optional<Role> optionalRole = roleRepo.findById(2L);
        optionalRole.ifPresent(value -> role = value);

        user.setRole(role);
        userRepo.save(user);

        //generate an account and assign to this user
        user = userRepo.findById(user.getId()).get();
        Long userAcctNum = accountNumber.generateAcctNumber();
        account = new Account(user, userAcctNum);
        account.setAcctStatus("Active");
        account.setUser(user);
        accountRepo.save(account);

        return user;
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
        Long userAcctNum = accountNumber.generateAcctNumber();
        account = new Account(user, userAcctNum);
        account.setAcctStatus("Active");
        account.setUser(user);
        accountRepo.save(account);

        return account;
    }

}
