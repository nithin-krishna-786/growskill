package com.alippo.growskill.security;

import com.alippo.growskill.dto.LogInDTO;
import com.alippo.growskill.dto.StudentDTO;
import com.alippo.growskill.entities.User;

public interface AuthService {
    String login(LogInDTO loginDto);
    User findByEmail(String email);
}
