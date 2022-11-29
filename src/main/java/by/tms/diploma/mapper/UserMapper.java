package by.tms.diploma.mapper;

import by.tms.diploma.dto.UserDto;
import by.tms.diploma.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User convertUserDtoToUser(UserDto userDto) {
        return new User(userDto.getUsername(), userDto.getPassword(), userDto.getFirstName(), userDto.getSecondName(), userDto.getDepartment(), userDto.getRoles());
    }
}
