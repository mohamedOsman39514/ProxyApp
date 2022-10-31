package com.example.proxy.rest.handler;

import com.example.proxy.model.Role;
import com.example.proxy.rest.dto.RoleDto;
import com.example.proxy.rest.dto.common.PaginationResponse;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.mapper.RoleMapper;
import com.example.proxy.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize){
        Page<Role> roles = roleService.getAll(pageNo, pageSize);
        List<Role> roleList = roles.getContent();
        List<RoleDto> content= roleList.stream().map(role ->  roleMapper.toRoleDto(role)).collect(Collectors.toList());
        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(content);
        paginationResponse.setPageNo(roles.getNumber()+1);
        paginationResponse.setPageSize(roles.getSize());
        paginationResponse.setTotalElements(roles.getTotalElements());
        paginationResponse.setTotalPages(roles.getTotalPages());

        return ResponseEntity.ok(paginationResponse);
    }
}
