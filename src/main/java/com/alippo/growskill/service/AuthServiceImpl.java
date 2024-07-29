package com.alippo.growskill.service;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alippo.growskill.dto.InstructorDTO;
import com.alippo.growskill.dto.LoginDTO;
import com.alippo.growskill.dto.StudentDTO;
import com.alippo.growskill.dto.UserDTO;
import com.alippo.growskill.entities.Instructor;
import com.alippo.growskill.entities.Role;
import com.alippo.growskill.entities.Student;
import com.alippo.growskill.entities.User;
import com.alippo.growskill.exceptions.UserAlreadyExistsException;
import com.alippo.growskill.repository.InstructorRepository;
import com.alippo.growskill.repository.RoleRepository;
import com.alippo.growskill.repository.StudentRepository;
import com.alippo.growskill.repository.UserRepository;
import com.alippo.growskill.security.JwtTokenProvider;
import com.alippo.growskill.util.Constants;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

	private UserRepository userRepository;
	private StudentRepository studentRepository;
	private InstructorRepository instructorRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	private ModelMapper modelMapper;

	@Override
	public InstructorDTO registerAsInstructor(InstructorDTO instructorDTO) {

		// check username is already exists in database
		if (userRepository.existsByUsername(instructorDTO.getUsername())) {
			throw new UserAlreadyExistsException("Username already exists!");
		}

		// check email is already exists in database
		if (userRepository.existsByEmail(instructorDTO.getEmail())) {
			throw new UserAlreadyExistsException("Email is already exists!");
		}

		Instructor instructor = modelMapper.map(instructorDTO, Instructor.class);
		instructor.setPassword(passwordEncoder.encode(instructorDTO.getPassword()));

		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_INSTRUCTOR");
		roles.add(userRole);
		instructor.setRoles(roles);

		instructor.setVerified(false);

		int passcode = generatePasscode(Constants.PASSCODE_LENGTH);
		instructor.setPassCode(String.valueOf(passcode));

		instructor = instructorRepository.save(instructor);
		instructorDTO = modelMapper.map(instructor, InstructorDTO.class);

		return instructorDTO;
	}

	@Override
	public StudentDTO registerAsStudent(StudentDTO studentDTO) {
		
		// check username is already exists in database
		if (userRepository.existsByUsername(studentDTO.getUsername())) {
			throw new UserAlreadyExistsException("Username already exists!");
		}

		// check email is already exists in database
		if (userRepository.existsByEmail(studentDTO.getEmail())) {
			throw new UserAlreadyExistsException("Email is already exists!");
		}

		Student student = modelMapper.map(studentDTO, Student.class);
		student.setPassword(passwordEncoder.encode(studentDTO.getPassword()));

		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_STUDENT");
		roles.add(userRole);
		student.setRoles(roles);

		student.setVerified(false);

		int passcode = generatePasscode(Constants.PASSCODE_LENGTH);
		student.setPassCode(String.valueOf(passcode));

		student = studentRepository.save(student);
		studentDTO = modelMapper.map(student, StudentDTO.class);

		return studentDTO;
	}
	
	@Override
	public UserDTO registerAsAdmin(UserDTO userDTO) {
		// check username is already exists in database
		if (userRepository.existsByUsername(userDTO.getUsername())) {
			throw new UserAlreadyExistsException("Username already exists!");
		}

		// check email is already exists in database
		if (userRepository.existsByEmail(userDTO.getEmail())) {
			throw new UserAlreadyExistsException("Email is already exists!");
		}

		User user = modelMapper.map(userDTO, User.class);
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_ADMIN");
		roles.add(userRole);
		user.setRoles(roles);

		user.setVerified(false);

		int passcode = generatePasscode(Constants.PASSCODE_LENGTH);
		user.setPassCode(String.valueOf(passcode));

		user = userRepository.save(user);
		userDTO = modelMapper.map(user, UserDTO.class);

		return userDTO;
	}
	
	
	public static int generatePasscode(int length) {
		Random random = new Random();
		int passcode = random.nextInt((int) Math.pow(10, length));
		return passcode;
	}

	@Override
	public String login(LoginDTO loginDTO) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenProvider.generateToken(authentication);

		return token;
	}





}
