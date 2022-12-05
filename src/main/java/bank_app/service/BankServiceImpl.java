package bank_app.service;

import bank_app.converter.UserConverter;
import bank_app.dto.UserDTO;
import bank_app.entity.Account;
import bank_app.entity.AcctNumGenerator;
import bank_app.entity.Role;
import bank_app.entity.User;
import bank_app.exception.ApiRequestException;
import bank_app.repo.AccountRepo;
import bank_app.repo.AcctNumGeneratorRepo;
import bank_app.repo.RoleRepo;
import bank_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private User user;
    @Autowired
    UserConverter userConverter;
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
        Optional<AcctNumGenerator> acctNumGeneratorOptional = acctNumGeneratorRepo.findById(1L);
        acctNumGeneratorOptional.ifPresent(value -> acctNumGenerator = value);
        String concatAcctNum = String.format("%s%s%s",
                acctNumGenerator.getBankCode(),
                acctNumGenerator.getBranchCode(),
                acctNumGenerator.getCustomerCode());

        //increment the customerCode in the db and save for next usage
        acctNumGenerator.setCustomerCode(acctNumGenerator.getCustomerCode() + 1);

        //insert bvn update statement here


        acctNumGeneratorRepo.save(acctNumGenerator);

        return Long.parseLong(concatAcctNum);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        user = userConverter.convertDtoToEntity(userDTO);

        //encrypt the password & confirmPassword provided by the user
        String hashedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        user.setPassword(hashedPassword);

        String hashedConfirmPassword = bCryptPasswordEncoder.encode(userDTO.getConfirmPassword());
        user.setConfirmPassword(hashedConfirmPassword);

        if(Objects.equals(userDTO.getPassword(), userDTO.getConfirmPassword())) {

            //assign from the db the role type with the id of 2
            Optional<Role> optionalRole = roleRepo.findById(2L);
            optionalRole.ifPresent(value -> role = value);
            user.setRole(role);

            //convert string to date and assign to user
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(userDTO.getDateOfBirth(), formatter);
            user.setDateOfBirth(date);

            userRepo.save(user);

            //generate an account and assign to this newly created user
            Optional<User> optionalUser = userRepo.findById(user.getId());
            optionalUser.ifPresent(value -> user = value);
            Long userAcctNum = generateAcctNumber();

            //
            account = new Account(user, userAcctNum);

            if(user.getAcctType() == 1) {
                account.setAcctType("Savings");
            } else if (user.getAcctType() == 2) {
                account.setAcctType("Current");
            } else {

                //customise the exception to throw here
                throw new ApiRequestException("Incomplete details provided");
            }

            account.setUser(user);
            accountRepo.save(account);

            return userConverter.convertEntityToDto(user);

        } else {

            //customise the exception to throw here
            throw new ApiRequestException("Password and Confirm password not the same");

        }
    }

    @Override
    public User fetchUserByEmail(UserDTO userDTO) {

        user = userConverter.convertDtoToEntity(userDTO);
        return userRepo.findByEmail(user.getEmail());
    }

    @Override
    public List<UserDTO> fetchUserList() {
        
        List<User> userList = userRepo.findAll();
        return userConverter.convertEntityListToDto(userList);

    }

    @Override
    public void deleteAccountByEmail(UserDTO userDTO) {
        user = userConverter.convertDtoToEntity(userDTO);
        user = userRepo.findByEmail(userDTO.getEmail());

        if(user != null ) {
            accountRepo.deleteAll(user.getAccounts());

        } else {
            throw new ApiRequestException("Cannot delete account");
        }
    }

    @Override
    public UserDTO updateUser(UserDTO userUpdate) {

        user = userConverter.convertDtoToEntity(userUpdate);

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

        userRepo.save(user);

        return userConverter.convertEntityToDto(user);
    }


    @Override
    public Account createAccount(UserDTO userDTO) {
        user = userConverter.convertDtoToEntity(userDTO);
        user = userRepo.findByEmail(userDTO.getEmail());
        Long userAcctNum = generateAcctNumber();
        account = new Account(user, userAcctNum);
        account.setUser(user);
        accountRepo.save(account);

        return account;
    }
}
