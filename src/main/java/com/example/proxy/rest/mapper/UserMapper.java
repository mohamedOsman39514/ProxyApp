package com.example.proxy.rest.mapper;

import com.example.proxy.model.User;
import com.example.proxy.rest.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    List<UserDto> toUserDtos(List<User> users);

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);

}
