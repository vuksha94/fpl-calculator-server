package com.springboot.fplcalculatorserver.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.springboot.fplcalculatorserver.entities.User;
import com.springboot.fplcalculatorserver.models.RegisterRequest;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

	@Mock
	UserService userService;
	
	
	@Test
	void testRegisterUserAlreadyExisting() {
		User user = new User(12, "asd@asd.com", "123", "Stefan", "Vukasinovic");
		
		when(userService.findUserByEmail("asd@asd.com")).thenReturn(Optional.of(user));
		when(userService.registerUser(Mockito.any(RegisterRequest.class))).thenCallRealMethod();
		
		RegisterRequest registerRequestExistingEmail = new RegisterRequest(328983, "asd@asd.com", "123", "123");
		assertThrows(RuntimeException.class, () -> userService.registerUser(registerRequestExistingEmail));
	}
	
	@Test
	void testRegisterUserPasswordConfirmationError() {
		when(userService.findUserByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		when(userService.registerUser(Mockito.any(RegisterRequest.class))).thenCallRealMethod();
		
		RegisterRequest registerRequest = new RegisterRequest(12, "asd@asd.com", "123", "321");
		assertThrows(RuntimeException.class, () -> userService.registerUser(registerRequest));
	}
	
/* To Do
	@Test
	void testRegisterUser() {		
		when(userService.findUserByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		when(userService.registerUser(Mockito.any(RegisterRequest.class))).thenCallRealMethod();
		
		RegisterRequest registerRequest = new RegisterRequest(328983, "asd@asd.com", "12345", "12345");
		User registerUser = userService.registerUser(registerRequest);
		assertTrue(registerUser.getFplManagerId() == registerRequest.getManagerId());
		assertTrue(registerUser.getEmail() == registerRequest.getEmail());
		assertTrue(registerUser.getPassword() == registerRequest.getPassword());
	}
*/
}
