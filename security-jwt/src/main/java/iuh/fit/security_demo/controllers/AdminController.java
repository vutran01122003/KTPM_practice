package iuh.fit.security_demo.controllers;

import iuh.fit.security_demo.dtos.request.RegisterUserDto;
import iuh.fit.security_demo.dtos.response.UserResponse;
import iuh.fit.security_demo.entities.User;
import iuh.fit.security_demo.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class AdminController {
    private final UserService userService;

    public AdminController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public UserResponse createAdministrator(@RequestBody RegisterUserDto request) throws Exception {
        User user = userService.createAdministrator(request);

        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getRealUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole().getName().toString());
        return userResponse;
    }
}
