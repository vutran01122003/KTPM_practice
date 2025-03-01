package iuh.fit.security_demo.services;

import iuh.fit.security_demo.dtos.request.RegisterUserDto;
import iuh.fit.security_demo.dtos.response.UserResponse;
import iuh.fit.security_demo.entities.Role;
import iuh.fit.security_demo.entities.User;
import iuh.fit.security_demo.repositories.RoleRepository;
import iuh.fit.security_demo.repositories.UserRepository;
import iuh.fit.security_demo.shared.RoleEnum;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(user -> {
            UserResponse userResponse = new UserResponse();
            userResponse.setUsername(user.getRealUsername());
            userResponse.setEmail(user.getEmail());
            userResponse.setRole(user.getRole().getName().toString());

            return userResponse;
        }).toList();
    }

    public User createAdministrator(RegisterUserDto request) throws Exception {
        Role role = roleRepository.findRoleByName(RoleEnum.ADMIN);

        if(userRepository.findUserByEmail(request.getEmail()) != null)
            throw new Exception("User has been existed");

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        return userRepository.save(user);
    }
}
