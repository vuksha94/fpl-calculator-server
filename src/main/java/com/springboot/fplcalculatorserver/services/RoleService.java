package com.springboot.fplcalculatorserver.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.fplcalculatorserver.accessingdatajpa.RoleRepository;
import com.springboot.fplcalculatorserver.entities.Role;

@Service
public class RoleService {
  @Autowired
  RoleRepository roleRepository;
  
  Optional<Role> findByRole(String role) {
    return roleRepository.findByRole(role);
  }
}
