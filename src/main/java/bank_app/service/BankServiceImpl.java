package bank_app.service;

import bank_app.entity.Account;
import bank_app.entity.AcctNumGenerator;
import bank_app.entity.Role;
import bank_app.entity.User;
import bank_app.repo.AccountRepo;
import bank_app.repo.AcctNumGeneratorRepo;
import bank_app.repo.RoleRepo;
import bank_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private User user;
    @Autowired
    private UserRepo userRepo;

    private Role role;
    @Autowired
    private RoleRepo roleRepo;

    private Account account;
    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private AcctNumGenerator acctNumGenerator;

    @Autowired
    private AcctNumGeneratorRepo acctNumGeneratorRepo;

    public synchronized Long generateAcctNumber() {
        acctNumGenerator = acctNumGeneratorRepo.findById(1L).get();
        String concatAcctNum = String.format("%s%s%s",
                acctNumGenerator.getBankCode(),
                acctNumGenerator.getBranchCode(),
                acctNumGenerator.getCustomerCode());

        //increment the customerCode in the db and save for next usage
        acctNumGenerator.setCustomerCode(acctNumGenerator.getCustomerCode() + 1);
        acctNumGeneratorRepo.save(acctNumGenerator);

        return Long.parseLong(concatAcctNum);
    }

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

        //generate an account and assign to this newly created user
        user = userRepo.findById(user.getId()).get();
        Long userAcctNum = generateAcctNumber();

        //
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
        Long userAcctNum = generateAcctNumber();
        account = new Account(user, userAcctNum);
        account.setAcctStatus("Active");
        account.setUser(user);
        accountRepo.save(account);

        return account;
    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepo.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User userUpdate) {
        user = userRepo.findById(id).get();
        List<Account> accounts = user.getAccounts();

        if(Objects.nonNull(userUpdate.getFirstName()) && !"".equalsIgnoreCase(userUpdate.getFirstName())) {
            user.setFirstName(userUpdate.getFirstName());

            //check the account attached to the provided user and update account name
            accounts.forEach(account1 -> account1.setAcctFirstName(user.getFirstName()));
        }

        if(Objects.nonNull(userUpdate.getLastName()) && !"".equalsIgnoreCase(userUpdate.getLastName())) {
            user.setLastName(userUpdate.getLastName());
            accounts.forEach(account1 -> account1.setAcctLastName(user.getLastName()));
        }

        if(Objects.nonNull(userUpdate.getEmail()) &&
                !"".equalsIgnoreCase(userUpdate.getEmail())) {
            user.setEmail(userUpdate.getEmail());
        }

        if(Objects.nonNull(userUpdate.getAddress()) &&
                !"".equalsIgnoreCase(userUpdate.getAddress())) {
            user.setAddress(userUpdate.getAddress());
        }


        if(Objects.nonNull(userUpdate.getUsername()) &&
                !"".equalsIgnoreCase(userUpdate.getUsername())) {
            user.setUsername(userUpdate.getUsername());
        }

        return userRepo.save(user);
    }

}
