package com.example.proxy.service;

import com.example.proxy.model.Request;
import com.example.proxy.model.Role;
import com.example.proxy.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;

    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    public List<Role> findAll()
    {
        return roleRepository.findAll();
    }

    public List<Role> getPostsList(int page, int size) {

        PageRequest pageReq = PageRequest.of(page, size);
        Page<Role> posts = roleRepository.findAll(pageReq);
        return posts.getContent();
    }
}
