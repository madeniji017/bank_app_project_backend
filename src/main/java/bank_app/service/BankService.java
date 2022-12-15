package bank_app.service;

import bank_app.dto.UserDTO;
import bank_app.entity.User;
import bank_app.error.BadRequestException;
import bank_app.error.UserNotFoundException;

import java.util.List;

public interface BankService {
    UserDTO createUser(UserDTO userDTO) throws BadRequestException;

    User fetchUserByEmail(UserDTO userDTO) throws UserNotFoundException;

    List<UserDTO> fetchUserList();

    UserDTO updateUser(UserDTO userUpdate) throws UserNotFoundException;

    void deleteAccountByEmail(UserDTO userDTO) throws UserNotFoundException;

    // Account createAccount(UserDTO userDTO);

}
