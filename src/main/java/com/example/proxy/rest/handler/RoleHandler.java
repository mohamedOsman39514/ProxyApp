package com.example.proxy.rest.handler;

import com.example.proxy.model.Role;
import com.example.proxy.rest.dto.RoleDto;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.mapper.RoleMapper;
import com.example.proxy.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RoleHandler {

    private RoleService roleService;
    private RoleMapper roleMapper;

    public ResponseEntity<RoleDto> getById(Long id) throws ResourceNotFound {
        Role role = roleService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("The role of id " + id + " Not Found"));
        RoleDto roleDto = roleMapper.toRoleDto(role);
        return ResponseEntity.ok(roleDto);
    }

    public ResponseEntity<List<?>> getAll() {
        List<RoleDto> toRoleDtos = roleMapper.toRoleDtos(roleService.findAll());
        return ResponseEntity.ok(toRoleDtos);
    }

    public List<RoleDto> getRoles(Integer page, Integer size) {
        List<Role> roles = roleService.getPostsList(page, size);
        return roles.stream().map(role -> roleMapper.toRoleDto(role)).collect(Collectors.toList());
    }
}
