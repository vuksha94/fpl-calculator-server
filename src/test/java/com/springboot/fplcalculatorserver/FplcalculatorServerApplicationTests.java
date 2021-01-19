package com.springboot.fplcalculatorserver;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.springboot.fplcalculatorserver.accessingdatajpa.ProjectRepository;
import com.springboot.fplcalculatorserver.accessingdatajpa.RoleRepository;
import com.springboot.fplcalculatorserver.accessingdatajpa.UserDetailsRepository;
import com.springboot.fplcalculatorserver.accessingdatajpa.UserRepository;
import com.springboot.fplcalculatorserver.entities.Role;
import com.springboot.fplcalculatorserver.entities.User;
import com.springboot.fplcalculatorserver.entities.UserDetails;

@SpringBootTest
class FplcalculatorServerApplicationTests {

  @Autowired
  UserRepository userRepository;
  @Autowired
  RoleRepository roleRepository;
  @Autowired
  ProjectRepository projectRepository;
  @Autowired
  UserDetailsRepository userDetailsRepository;
  
  private String email = "aaa.yyy@gmail.com";
  private String updatedEmail = "xxasdx5.yyy@gmail.com";
  
  @Test
  void injectedComponentsAreNotNull(){
       assertNotNull(userRepository);
       assertNotNull(roleRepository);
       assertNotNull(projectRepository);
       assertNotNull(userDetailsRepository);
  }
  
  @Test
  void testCreateUser() {
    User user = new User();
    user.setActive(true);
    user.setName("Test user");
    user.setLastName("Test");
    user.setFplManagerId(12);
    user.setEmail(email);
    user.setPassword("asd");
    userRepository.save(user);
    final Optional<User> createdUser = userRepository.findByEmail(email);
    assertTrue(createdUser.isPresent(), () -> "User creation failed!");
  }
  
  @Test
  void testUpdateUsersName() {
    final Optional<User> user = userRepository.findByEmail(email);
    assertTrue(user.isPresent(), () -> "User not found!");
    user.ifPresent(u -> u.setEmail(updatedEmail));
    userRepository.save(user.get());
    final Optional<User> findByEmail = userRepository.findByEmail(updatedEmail);
    assertTrue(findByEmail.isPresent(), () -> "Update failed!");
  }
  
  @Test
  @Transactional
  void testUserToStringMethod() {
    final User user = userRepository.findByEmail("some_guy.yyy@gmail.com").get();
    System.out.println(user);
  }
  
  
  @Test
  void testO2OAddUserDetails() {
    final User user = userRepository.findByEmail(updatedEmail).get();
    final UserDetails userDetails = new UserDetails();
    user.setDetails(userDetails);
    userRepository.save(user);
    
  }
  
  @Test
  void testReadManagerLeagues() {
    final User user = userRepository.findByEmail("stefan.vukasinovic994@gmail.com").get();
    user.getManagerLeagues().stream()
                            .forEach(league -> System.out.println(league.getName()));
    //System.out.println(user.getDetails().getId());
  }
  
  @Test
  void testCreateRoles() {
    List<Role> roles = new ArrayList<>();
    Role userRole = new Role();
    userRole.setRole("ROLE_USER");
    Role adminRole = new Role();
    adminRole.setRole("ROLE_ADMIN");
    roles.add(userRole);
    roles.add(adminRole);
    
    roleRepository.saveAll(roles);
    
  }
  
  @Test
  void testAddUserRoles()  {
    final User user = userRepository.findByEmail("xxasdx5.yyy@gmail.com").get();
    final Role roleUser = roleRepository.findByRole("ROLE_USER").get();
    final Role roleTest = roleRepository.findByRole("ROLE_ADMIN").get();
    /*final Role roleUser = new Role();
    roleUser.setRole("T1");
    final Role roleTest = new Role();
    roleTest.setRole("T2");*/

    final List<Role> roles = new ArrayList<>();
    roles.add(roleUser);
    //roles.add(roleTest);
    user.setRoles(roles);
    userRepository.save(user);
    
    //final User user1 = userRepository.findByEmail("xxasdx5.yyy@gmail.com").get();
    
  }

  
  @Test
  @Transactional
  void testReadUserRoles()  {
    final User user = userRepository.findByEmail("xxasdx5.yyy@gmail.com").get();
    user.getRoles().stream()
                   .forEach(System.out::println);
  }
  

}
