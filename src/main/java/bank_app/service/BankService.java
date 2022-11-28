package bank_app.service;

import bank_app.entity.Account;
import bank_app.entity.User;

import java.util.List;

public interface BankService {
    User saveUser(User user);

    User fetchUserById(Long id);

    List<User> fetchUserList();

    Account createAccount(Long id);

    void deleteAccountById(Long id);

    User updateUser(Long id, User userUpdate);
}
