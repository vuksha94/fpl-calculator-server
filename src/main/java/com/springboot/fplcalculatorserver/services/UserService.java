package com.springboot.fplcalculatorserver.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.springboot.fplcalculatorserver.accessingdatajpa.UserRepository;
import com.springboot.fplcalculatorserver.entities.LeagueDetails;
import com.springboot.fplcalculatorserver.entities.Role;
import com.springboot.fplcalculatorserver.entities.User;
import com.springboot.fplcalculatorserver.exceptions.ErrorCode;
import com.springboot.fplcalculatorserver.exceptions.MyAuthenticationException;
import com.springboot.fplcalculatorserver.models.MyUserDetails;
import com.springboot.fplcalculatorserver.models.RegisterRequest;
import com.springboot.fplcalculatorserver.models.fpl.FplLeagueDetails;
import com.springboot.fplcalculatorserver.models.fpl.FplManagerDetails;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleService roleService;
  
  @Autowired
  private BussinessLogicService bussinessLogicService;

  @Autowired
  private FplApiService fplApiService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public UserDetails findByEmail(String email) throws UsernameNotFoundException {
    final Optional<User> user = findUserByEmail(email);

    user.orElseThrow(() -> new UsernameNotFoundException("Email: " + email + " not found!"));

    return user.map(MyUserDetails::new).get();
  }

  public Optional<User> findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }


  public User registerUser(RegisterRequest registerRequest) throws RuntimeException {
    final long fplManagerId = registerRequest.getFplManagerId();
    final String email = registerRequest.getEmail();
    final String password = registerRequest.getPassword();
    final String confirmPassword = registerRequest.getConfirmPassword();

    findUserByEmail(email).ifPresent(u -> {
      throw new MyAuthenticationException(ErrorCode.EMAIL_TAKEN.getMsg(), ErrorCode.EMAIL_TAKEN);
    });
    if (!password.equals(confirmPassword)) {
      throw new MyAuthenticationException(ErrorCode.PASSWORD_CONFIRMATION_ERROR.getMsg(),
          ErrorCode.PASSWORD_CONFIRMATION_ERROR);
    }
    FplManagerDetails managersDetails = null;
    
    managersDetails = fplApiService.getManagersDetails(fplManagerId);
    final List<FplLeagueDetails> classicLeagues = managersDetails.getLeagues().getClassic();
    final List<LeagueDetails> leagues = bussinessLogicService.getLeagueDetails(classicLeagues);
    
    String hashedPassword = passwordEncoder.encode(password);
    User registerUser = new User();
    registerUser.setActive(true);
    registerUser.setFplManagerId(fplManagerId);
    registerUser.setEmail(email);
    registerUser.setPassword(hashedPassword);
    registerUser.setName(managersDetails.getPlayer_first_name());
    registerUser.setLastName(managersDetails.getPlayer_last_name());
    registerUser.setManagerLeagues(leagues);
    addUserRoleToUser(registerUser); // set role to ROLE_USER ******************TO DO*****************

    return userRepository.save(registerUser);
  }

  public void addUserRoleToUser(User user) {
    final Optional<Role> role = roleService.findByRole("ROLE_USER");
    List<Role> roles = new ArrayList<>(Arrays.asList(role.get()));
    user.setRoles(roles);
  }
  
  public void addAdminRoleToUser(User user) {
    final Optional<Role> role = roleService.findByRole("ROLE_ADMIN");
    List<Role> roles = new ArrayList<>(Arrays.asList(role.get()));
    user.setRoles(roles);
  }

}
