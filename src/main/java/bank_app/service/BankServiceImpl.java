package bank_app.service;

import bank_app.converter.UserConverter;
import bank_app.dto.UserDTO;
import bank_app.entity.Account;
import bank_app.entity.AcctNumGenerator;
import bank_app.entity.Role;
import bank_app.entity.User;
import bank_app.error.BadRequestException;
import bank_app.error.UserNotFoundException;
import bank_app.repo.AccountRepo;
import bank_app.repo.AcctNumGeneratorRepo;
import bank_app.repo.RoleRepo;
import bank_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BankServiceImpl implements BankService {

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


        acctNumGeneratorRepo.save(acctNumGenerator);

        return Long.parseLong(concatAcctNum);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) throws BadRequestException {

        user = userConverter.convertDtoToEntity(userDTO);

        //encrypt the password & confirmPassword provided by the user
        String hashedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        user.setPassword(hashedPassword);

        String hashedConfirmPassword = bCryptPasswordEncoder.encode(userDTO.getConfirmPassword());
        user.setConfirmPassword(hashedConfirmPassword);

        if(Objects.equals(userDTO.getPassword(), userDTO.getConfirmPassword())) {

            if(userDTO.getFirstName().length() < 3 ||
            userDTO.getLastName().length() < 3) {
                throw new BadRequestException("Length of first and last name must be a minimum of 3");
            }

            if(userDTO.getBvn().length() < 11 ||
                    userDTO.getPhoneNumber().length() < 11) {
                throw new BadRequestException("Length of bvn or phone number must be a minimum of 11");
            }


            //assign from the db the role type with the id of 2
            Optional<Role> optionalRole = roleRepo.findById(2L);
            optionalRole.ifPresent(value -> role = value);
            user.setRole(role);

            //convert string to date and assign to user
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(userDTO.getDateOfBirth(), formatter);
            user.setDateOfBirth(date);

            //check this line very well
            userRepo.save(user);

            //generate an account and assign to this newly created user
            Optional<User> optionalUser = userRepo.findById(user.getId());
            optionalUser.ifPresent(value -> user = value);
            Long userAcctNum = generateAcctNumber();

            //
            Account account = new Account(user, userAcctNum);

            if(user.getAcctType() == null) {
                throw new BadRequestException("Please provide a valid account type");
            } else if (user.getAcctType() == 1) {
                account.setAcctType("Savings");
            } else if(user.getAcctType() == 2) {
                account.setAcctType("Current");
            } else {
                throw new BadRequestException("Please provide a valid account type");
            }


            account.setUser(user);
            accountRepo.save(account);

            List<Account> accounts = new ArrayList<>();
            accounts.add(account);

            user.setAccounts(accounts);

            return userConverter.convertEntityToDto(user);

        } else {

            throw new BadRequestException("Password and Confirm password not the same");

        }
    }

    @Override
    public User fetchUserByEmail(UserDTO userDTO) throws UserNotFoundException {

        user = userConverter.convertDtoToEntity(userDTO);
        user = userRepo.findByEmail(user.getEmail());
        if(user == null){

            throw new UserNotFoundException("No user found with the email");

        }else{

            return user;
        }
    }

    @Override
    public List<UserDTO> fetchUserList() {
        
        List<User> userList = userRepo.findAll();
        return userConverter.convertEntityListToDto(userList);

    }

    @Override
    public void deleteAccountByEmail(UserDTO userDTO) throws UserNotFoundException {
        user = userConverter.convertDtoToEntity(userDTO);
        user = userRepo.findByEmail(userDTO.getEmail());

        if(user != null ) {
            accountRepo.deleteAll(user.getAccounts());

        } else {
            throw new UserNotFoundException("Cannot delete. No user with provided email");
        }
    }

    @Override
    public UserDTO updateUser(UserDTO userUpdate) throws UserNotFoundException {

        user = userConverter.convertDtoToEntity(userUpdate);
        user = userRepo.findByEmail(user.getEmail());

        if (user == null) {

            throw new UserNotFoundException("No user with the provided email");

        } else {

            List<Account> accounts = user.getAccounts();

            if (Objects.nonNull(userUpdate.getFirstName()) && !"".equalsIgnoreCase(userUpdate.getFirstName())) {
                user.setFirstName(userUpdate.getFirstName());

                //check the account attached to the provided user and update account name
                accounts.forEach(account1 -> account1.setAcctFirstName(user.getFirstName()));
            }

            if (Objects.nonNull(userUpdate.getLastName()) && !"".equalsIgnoreCase(userUpdate.getLastName())) {
                user.setLastName(userUpdate.getLastName());
                accounts.forEach(account1 -> account1.setAcctLastName(user.getLastName()));
            }

            if (Objects.nonNull(userUpdate.getEmail()) &&
                    !"".equalsIgnoreCase(userUpdate.getEmail())) {
                user.setEmail(userUpdate.getEmail());

            }

            userRepo.save(user);

        }

        return userConverter.convertEntityToDto(user);
    }
}

