package iuh.fit.security_demo.controllers;

import iuh.fit.security_demo.dtos.request.RegisterUserDto;
import iuh.fit.security_demo.dtos.response.UserResponse;
import iuh.fit.security_demo.entities.User;
import iuh.fit.security_demo.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public UserResponse getMe() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getRealUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole().getName().toString());

        return userResponse;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }
}
