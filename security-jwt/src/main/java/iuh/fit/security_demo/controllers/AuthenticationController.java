package iuh.fit.security_demo.controllers;

import iuh.fit.security_demo.dtos.request.LoginUserDto;
import iuh.fit.security_demo.dtos.request.RegisterUserDto;
import iuh.fit.security_demo.dtos.response.LoginResponse;
import iuh.fit.security_demo.dtos.response.UserResponse;
import iuh.fit.security_demo.entities.User;
import iuh.fit.security_demo.services.AuthenticationService;
import iuh.fit.security_demo.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthenticationController(final AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody RegisterUserDto request) throws Exception {
        return ResponseEntity.status(200).body(authenticationService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto request) throws Exception {
        User user = authenticationService.authenticate(request.getEmail(), request.getPassword());

        String token = jwtService.generateToken(user, new HashMap<>());

        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getRealUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole().getName().toString());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUser(userResponse);
        loginResponse.setToken(token);

        return ResponseEntity.status(200).body(loginResponse);
    }
}
