package com.itransition.courseproject.service;

import com.itransition.courseproject.dto.request.LoginDto;
import com.itransition.courseproject.dto.request.UserRegisterDto;
import com.itransition.courseproject.dto.response.UserBasicResponseTo;

public interface AuthService {
    UserBasicResponseTo loginToSystem(LoginDto loginDto);
    UserBasicResponseTo register(UserRegisterDto registerDto);
    Boolean checkEmail(String email);
}
