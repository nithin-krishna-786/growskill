package com.alippo.growskill.security;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alippo.growskill.dto.LogInDTO;
import com.alippo.growskill.dto.StudentRegisterDTO;
import com.alippo.growskill.entities.Role;
import com.alippo.growskill.entities.Student;
import com.alippo.growskill.entities.User;
import com.alippo.growskill.exceptions.UserAlreadyExistsException;
import com.alippo.growskill.exceptions.UserException;
import com.alippo.growskill.exceptions.UserNotFoundException;
import com.alippo.growskill.repository.RoleRepository;
import com.alippo.growskill.repository.StudentRepository;
import com.alippo.growskill.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

	private UserRepository userRepository;
	private StudentRepository studentRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;


	@Override
	public String login(LogInDTO loginDto) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenProvider.generateToken(authentication);

		return token;
	}
	
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User not found for email:" + email));

		return user;
	}

}
