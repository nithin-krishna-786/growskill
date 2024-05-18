package com.alippo.growskill.service;

import com.alippo.growskill.entities.User;
import com.alippo.growskill.exceptions.UserException;

public interface IUserService {
	public User findUserById(Long userId) throws UserException;
	public User findUserProfileByJwt(String jwt) throws UserException;
}
