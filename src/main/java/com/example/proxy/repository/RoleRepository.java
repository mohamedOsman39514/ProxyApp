package com.example.proxy.repository;

import com.example.proxy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends JpaRepository<Role, Long> , PagingAndSortingRepository<Role, Long> {
}
