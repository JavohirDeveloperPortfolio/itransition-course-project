package com.itransition.courseproject.service.impl;

import com.itransition.courseproject.dto.request.LoginDto;
import com.itransition.courseproject.dto.request.UserRegisterDto;
import com.itransition.courseproject.dto.response.ErrorResponse;
import com.itransition.courseproject.dto.response.UserBasicResponseTo;
import com.itransition.courseproject.entity.RoleEntity;
import com.itransition.courseproject.entity.UserEntity;
import com.itransition.courseproject.enums.RoleTypeEnum;
import com.itransition.courseproject.exception.GlobalException;
import com.itransition.courseproject.exception.WrongLoginPasswordException;
import com.itransition.courseproject.repository.UserRepository;
import com.itransition.courseproject.security.jwt.JwtProvider;
import com.itransition.courseproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException{
        Optional<UserEntity> user = userRepository.findByEmailAndEnabledTrue(email);
        if (!user.isPresent()){
            throw new UsernameNotFoundException("");
        }
        return user.get();
    }

    @Override
    public UserBasicResponseTo loginToSystem(LoginDto loginDto) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );
            UserEntity user = (UserEntity) authentication.getPrincipal();
            String token = jwtProvider.generateToken(user.getEmail());
            UserBasicResponseTo userBasicResponseTo = user.toBasic();
            userBasicResponseTo.setToken(token);
            return userBasicResponseTo;
    }

    @Override
    public UserBasicResponseTo register(UserRegisterDto registerDto) {
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        UserEntity user = modelMapper.map(registerDto, UserEntity.class);
        user.setRoles(new HashSet<RoleEntity>(){{
            add(new RoleEntity(1,RoleTypeEnum.ROLE_USER));
        }});

        user = userRepository.save(user);
        String token = jwtProvider.generateToken(user.getEmail());
        UserBasicResponseTo userBasicResponseTo = new UserBasicResponseTo(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRoles(),
                token
        );
        return userBasicResponseTo;
    }

    @Override
    public Boolean checkEmail(String email) {
        return userRepository.existsUserEntityByEmail(email);
    }

}
