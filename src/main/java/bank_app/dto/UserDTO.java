package bank_app.dto;

import bank_app.entity.Account;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String username;

    private String password;

    private List<Account> accounts;

}
