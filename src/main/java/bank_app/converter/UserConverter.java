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
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;

    }

    public List<UserDTO> convertEntityListToDto(List<User> userList) {
        return userList.stream().map(x -> convertEntityToDto(x)).collect(Collectors.toList());
    }

    public User convertDtoToEntity(UserDTO userDTO) {

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDTO, User.class);

        return  user;
    }

    public List<User> convertDtoListToEntity(List<UserDTO> userDTOList) {
        return userDTOList.stream().map(x -> convertDtoToEntity(x)).collect(Collectors.toList());
    }


}
