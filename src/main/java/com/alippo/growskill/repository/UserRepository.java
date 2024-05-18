package com.alippo.growskill.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alippo.growskill.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Boolean existsByUsername(String userName);
	Boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);
	Optional<User> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail2);

}
