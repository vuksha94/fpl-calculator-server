package com.springboot.fplcalculatorserver.accessingdatajpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.fplcalculatorserver.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByRole(String role);
}
