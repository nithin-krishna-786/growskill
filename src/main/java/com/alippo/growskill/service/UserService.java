package com.alippo.growskill.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alippo.growskill.entities.User;
import com.alippo.growskill.exceptions.UserException;
import com.alippo.growskill.repository.UserRepository;
import com.alippo.growskill.security.JwtTokenProvider;

@Service
public class UserService implements IUserService {

	private UserRepository userRepository;
	private JwtTokenProvider jwtTokenProvider;

	public UserService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
		this.userRepository = userRepository;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public User findUserById(Long userId) throws UserException {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			return user.get();
		}

		throw new UserException("User not found with id " + userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email = jwtTokenProvider.getEmailFromJwtToken(jwt);
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserException("user not exist with email " + email));
		return user;
	}

}
