package com.example.proxy.rest.mapper;

import com.example.proxy.model.Role;
import com.example.proxy.rest.dto.RoleDto;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface RoleMapper {

    List<RoleDto> toRoleDtos(List<Role> roles);

    Role toRole(RoleDto roleDto);

    RoleDto toRoleDto(Role role);
}
