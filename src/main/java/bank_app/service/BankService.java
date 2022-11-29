package bank_app.service;

import bank_app.dto.UserDTO;
import bank_app.entity.Account;
import bank_app.entity.User;

import java.util.List;

public interface BankService {
    UserDTO saveUser(UserDTO userDTO);

    User fetchUserById(Long id);

    List<UserDTO> fetchUserList();

    Account createAccount(Long id);

    void deleteAccountById(Long id);

    UserDTO updateUser(Long id, UserDTO userUpdate);
}
