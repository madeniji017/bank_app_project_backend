package bank_app.converter;

import bank_app.dto.UserDTO;
import bank_app.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public UserDTO convertEntityToDto(User user) {

        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);

        return dto;

    }

    public List<UserDTO> convertEntityListToDto(List<User> userList) {

        //lambda is replaced here with method reference
        List<User> user =  new ArrayList<>();
        return userList.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public User convertDtoToEntity(UserDTO userDTO) {

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);

        return user;
    }

    public List<User> convertDtoListToEntity(List<UserDTO> userDTOList) {
        return userDTOList.stream().map(this::convertDtoToEntity).collect(Collectors.toList());
    }


}
