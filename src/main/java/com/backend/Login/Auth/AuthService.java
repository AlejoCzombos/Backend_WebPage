package com.backend.Login.Auth;

import com.backend.Desktop.Entity.Parent;
import com.backend.Desktop.Entity.Student;
import com.backend.Desktop.Service.ParentService;
import com.backend.Desktop.Service.StudentService;
import com.backend.Login.User.Role;
import com.backend.Login.User.User;
import com.backend.Login.User.UserRepository;
import com.backend.Login.jwt.JwtService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final StudentService studentService;
    private final ParentService parentService;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .dni(request.getDni())
                .role(request.getRole())
                .build();
        userRepository.save(user);

        if (request.getRole() == Role.Estudiante){
            Student student = new Student(request.getFirstname(), request.getLastname());
            studentService.createManually(student, request.username);
        }else if (request.getRole() == Role.Padre){
            Parent parent = new Parent(request.getFirstname(), request.getLastname());
            parentService.createManually(parent, request.username);
        }


        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
