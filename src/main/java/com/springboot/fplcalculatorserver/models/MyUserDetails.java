package com.springboot.fplcalculatorserver.models;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.fplcalculatorserver.entities.Role;
import com.springboot.fplcalculatorserver.entities.User;

public class MyUserDetails implements UserDetails {
	
	private String email;
	private String password;
	private List<GrantedAuthority> roles;
	private boolean active;

	public MyUserDetails(User user) {
		email = user.getEmail();
		password= user.getPassword();
		roles = getRolesFromUser(user.getRoles());
		active = user.isActive();
	}
	
	private List<GrantedAuthority> getRolesFromUser(List<Role> roles) {
		return roles.stream()
			.map(role -> role.getRole())
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

	@Override
	public String toString() {
		return "MyUserDetails [email=" + email + ", password=" + password + ", roles=" + roles + ", active=" + active
				+ "]";
	}
	

}
