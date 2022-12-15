package bank_app.controller;

import bank_app.converter.UserConverter;
import bank_app.dto.UserDTO;
import bank_app.entity.User;
import bank_app.service.BankService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankController.class)
class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BankService bankService;
    @MockBean
    private UserConverter userConverter;

    private String baseUrl = "http://localhost:8080/api/v1";


    @Test
    void fetchUserList() throws Exception{

        List<User> userList = new ArrayList<>();

        User user1 = new User("Raji", "Fashola", "rfash@gmail.com");
        User user2 = new User("Phillip", "Ben", "pben@gmail.com");

        userList.add(user1);
        userList.add(user2);

        List<UserDTO> userDTOList = userConverter.convertEntityListToDto(userList);

        bankService.fetchUserList();

        Mockito.when(bankService.fetchUserList()).thenReturn(userDTOList);

        String url = baseUrl+"/user-list";

        mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();

    }

    @Test
    void createUser() throws Exception {

        User user1 = new User("Raji", "Fashola", "rfash@gmail.com");
        User user2 = new User("Phillip", "Ben", "pben@gmail.com");

        UserDTO userDTO1 = userConverter.convertEntityToDto(user1);
        UserDTO savedUserDTO = userConverter.convertEntityToDto(user2);

        Mockito.when(bankService.createUser(userDTO1)).thenReturn(savedUserDTO);

        String url = "/create-user";

        mockMvc.perform(
                post(url)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userDTO1))
        ).andExpect(status().isOk()).andReturn();
    }

    @Test
    void testEmailMustNotBeBlank() throws Exception {

        User user1 = new User("Raji", "Fashola", "");
        UserDTO userDTO1 = userConverter.convertEntityToDto(user1);

        String url = "/create-user";
        mockMvc.perform(
                post(url)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userDTO1))
        ).andExpect(status().isBadRequest());
    }

}