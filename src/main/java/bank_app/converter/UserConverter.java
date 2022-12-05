package bank_app.converter;

import bank_app.dto.UserDTO;
import bank_app.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public UserDTO convertEntityToDto(User user) {

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(user, UserDTO.class);

    }

    public List<UserDTO> convertEntityListToDto(List<User> userList) {

        //lambda is replaced here with method reference
        return userList.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public User convertDtoToEntity(UserDTO userDTO) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDTO, User.class);
    }

    public List<User> convertDtoListToEntity(List<UserDTO> userDTOList) {
        return userDTOList.stream().map(this::convertDtoToEntity).collect(Collectors.toList());
    }


}
