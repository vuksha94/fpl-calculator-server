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

import com.springboot.fplcalculatorserver.accessingdatajpa.RoleRepository;
import com.springboot.fplcalculatorserver.accessingdatajpa.UserRepository;
import com.springboot.fplcalculatorserver.entities.Role;
import com.springboot.fplcalculatorserver.entities.User;
import com.springboot.fplcalculatorserver.models.MyUserDetails;
import com.springboot.fplcalculatorserver.models.RegisterRequest;
import com.springboot.fplcalculatorserver.models.fpl.FplManagerDetails;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
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
		final long fplManagerId = registerRequest.getManagerId();
		final String email = registerRequest.getEmail();
		final String password = registerRequest.getPassword();
		final String confirmPassword = registerRequest.getConfirmPassword();
		
		findUserByEmail(email)
			.ifPresent(u -> {
				throw new RuntimeException("Email already taken!");
			});
		if(!password.equals(confirmPassword)) {
			throw new RuntimeException("Password confirmation error!");
		}
		final FplManagerDetails managersDetails = fplApiService.getManagersDetails(fplManagerId);
		String hashedPassword = passwordEncoder.encode(password);
		User registerUser = new User(fplManagerId, email, hashedPassword, 
				managersDetails.getPlayer_first_name(), managersDetails.getPlayer_last_name());
		addUserRoleToUser(registerUser); // set role to ROLE_USER
		
		return userRepository.save(registerUser);
	}
	
	private void addUserRoleToUser(User user) {
		final Optional<Role> role = roleRepository.findByRole("ROLE_USER");
		List<Role> roles = new ArrayList<>(Arrays.asList(role.get()));
		user.setRole(roles);
	}
	
}
