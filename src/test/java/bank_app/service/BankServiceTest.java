package bank_app.service;

import bank_app.converter.UserConverter;
import bank_app.dto.UserDTO;
import bank_app.entity.User;
import bank_app.error.UserNotFoundException;
import bank_app.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BankServiceTest {

    @Autowired
    private BankService bankService;
    @MockBean
    private UserRepo userRepo;
    @Autowired
    UserConverter userConverter;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .firstName("Sammo")
                .lastName("Lang")
                .email("slang@gmail.com")
                .phoneNumber("07011220054")
                .bvn("20020023031").build();

        UserDTO userDTO = userConverter.convertEntityToDto(user);

        Mockito.when(userRepo.findByEmail("slang@gmail.com")).thenReturn(user);
    }
    @Test
    void whenValidUserMail_thenUserIsFound() throws UserNotFoundException {

        UserDTO userDTO = userConverter.convertEntityToDto(User.builder()
                .email("slang@gmail.com").build());
        String userEmail = "slang@gmail.com";
        User found = bankService.fetchUserByEmail(userDTO);

        assertEquals(userEmail, found.getEmail());
    }
}