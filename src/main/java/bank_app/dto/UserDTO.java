package bank_app.dto;

import bank_app.entity.Account;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private String firstName;

    private String middleName;

    private String lastName;

    private String bvn;

    private String email;

    private String phoneNumber;

    private String password;

    private String confirmPassword;

    private String dateOfBirth;

    private Integer acctType;

    private List<Account> accounts;

}
