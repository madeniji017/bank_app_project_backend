package bank_app.service;

import bank_app.dto.UserDTO;
import bank_app.entity.User;

import java.util.List;

public interface BankService {
    UserDTO createUser(UserDTO userDTO);

    User fetchUserByEmail(UserDTO userDTO);

    List<UserDTO> fetchUserList();

    UserDTO updateUser(UserDTO userUpdate);

    void deleteAccountByEmail(UserDTO userDTO);

    // Account createAccount(UserDTO userDTO);

}
