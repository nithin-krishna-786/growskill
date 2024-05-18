package com.alippo.growskill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alippo.growskill.entities.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
