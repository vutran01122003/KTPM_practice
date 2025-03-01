package iuh.fit.security_demo.services;

import iuh.fit.security_demo.dtos.request.RegisterUserDto;
import iuh.fit.security_demo.entities.Role;
import iuh.fit.security_demo.entities.User;
import iuh.fit.security_demo.repositories.RoleRepository;
import iuh.fit.security_demo.repositories.UserRepository;
import iuh.fit.security_demo.shared.RoleEnum;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, RoleRepository repository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = repository;
    }

    public User signup(RegisterUserDto request) throws Exception {
        if(userRepository.findUserByEmail(request.getEmail()) != null) throw new Exception("User has been existed");
        Role role = roleRepository.findRoleByName(RoleEnum.USER);

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setRole(role);

        return userRepository.save(user);
    }

    public User authenticate(String email, String password) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        return userRepository.findUserByEmail(email);
    }
}
