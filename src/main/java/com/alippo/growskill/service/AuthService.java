package com.alippo.growskill.service;

import com.alippo.growskill.dto.InstructorDTO;
import com.alippo.growskill.dto.LoginDTO;
import com.alippo.growskill.dto.StudentDTO;
import com.alippo.growskill.dto.UserDTO;

public interface AuthService {
	InstructorDTO registerAsInstructor(InstructorDTO InstructorDTO);
	StudentDTO registerAsStudent(StudentDTO studentDTO);
	UserDTO registerAsAdmin(UserDTO userDTO);
    String login(LoginDTO loginDto);
}
