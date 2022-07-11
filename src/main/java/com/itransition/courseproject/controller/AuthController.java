package com.itransition.courseproject.controller;

import com.itransition.courseproject.dto.request.LoginDto;
import com.itransition.courseproject.dto.request.UserRegisterDto;
import com.itransition.courseproject.dto.response.ErrorResponse;
import com.itransition.courseproject.dto.response.UserBasicResponseTo;
import com.itransition.courseproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        UserBasicResponseTo userBasicResponseTo = authService.loginToSystem(loginDto);
        if (userBasicResponseTo.getToken() == null){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(405,"Error","Wrong login or password")
            );
        }
        return ResponseEntity.ok(userBasicResponseTo);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto userRegisterDto){
        if (authService.checkEmail(userRegisterDto.getEmail())){
            return ResponseEntity.badRequest().body(new ErrorResponse(400,"Error","User already exist"));
        }
        return ResponseEntity.ok(authService.register(userRegisterDto));
    }
}
