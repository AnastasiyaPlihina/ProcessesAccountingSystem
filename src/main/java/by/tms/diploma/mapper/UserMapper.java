package by.tms.diploma.mapper;

import by.tms.diploma.dto.UserDto;
import by.tms.diploma.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User convertUserDtoToUser(UserDto userDto);
}
