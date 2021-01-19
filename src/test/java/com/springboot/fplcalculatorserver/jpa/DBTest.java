package com.springboot.fplcalculatorserver.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.springboot.fplcalculatorserver.accessingdatajpa.RoleRepository;
import com.springboot.fplcalculatorserver.accessingdatajpa.UserRepository;
import com.springboot.fplcalculatorserver.entities.Role;
import com.springboot.fplcalculatorserver.entities.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class DBTest {

  @Autowired
  UserRepository userRepository;
  
  @Autowired
  RoleRepository roleRepository;
  
  @Test
  void assignRolesToUser() {
    final User user = userRepository.findByEmail("xxx.yyy@gmail.com").get();
    final Optional<Role> userRole = roleRepository.findByRole("ROLE_USER");
    final List<Role> roles = new ArrayList<>();
    roles.add(userRole.get());
    user.setRoles(roles);
    
    userRepository.save(user);
    
  }

}
